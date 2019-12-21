package com.example.tmdb.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.model.Movie
import com.example.tmdb.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var movieUuid = 0

    private lateinit var dataBinding: FragmentDetailBinding
    private lateinit var currentMovie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movieUuid = DetailFragmentArgs.fromBundle(it).movieUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(movieUuid)

        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModel(){
        viewModel.movieLiveData.observe(this, Observer { movie ->
            movie?.let {
                currentMovie = it
                dataBinding.movie = it
                activity?.title = it.title
            }
        })
    }
}
