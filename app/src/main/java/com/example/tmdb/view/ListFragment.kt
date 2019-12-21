package com.example.tmdb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: ListViewModel
    private lateinit var searchAdapter : MoviesListAdapter
    private lateinit var type : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        edtSearch.setOnQueryTextListener(this)

        type = ListFragmentArgs.fromBundle(arguments!!).type
        viewModel.refresh(type)

        recyclerMovies.apply {
            layoutManager = LinearLayoutManager(context)
        }

        refreshLayout.setOnRefreshListener {
            recyclerMovies.visibility = View.GONE
            tvListError.visibility = View.GONE
            pbLoading.visibility = View.VISIBLE
            viewModel.refreshFromRemote(type)
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                recyclerMovies.visibility = View.VISIBLE
                searchAdapter = MoviesListAdapter(ArrayList(movies))
                searchAdapter.updateMoviesList(it)
                recyclerMovies.adapter = searchAdapter
            }
        })

        viewModel.moviesLoadError.observe(this, Observer { isError ->
            isError?.let {
                tvListError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    pbLoading.visibility = View.VISIBLE
                    tvListError.visibility = View.GONE
                    recyclerMovies.visibility = View.GONE
                } else {
                    pbLoading.visibility = View.GONE
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText)
        return true
    }

    private fun search(s: String?) {
        searchAdapter.search(s) {
            Toast.makeText(activity, "No se encontraron películas con este criterio", Toast.LENGTH_SHORT).show()
        }
    }

}
