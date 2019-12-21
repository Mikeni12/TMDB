package com.example.tmdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.ItemMovieBinding
import com.example.tmdb.model.Movie
import com.example.tmdb.utils.DynamicSearchAdapter
import com.example.tmdb.utils.MovieClickListener
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesListAdapter(val movies: ArrayList<Movie>) : MovieClickListener,
    DynamicSearchAdapter<Movie>(movies) {

    fun updateMoviesList(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil
            .inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movies[position]
        holder.view.listener = this
    }

    override fun onMovieClicked(view: View) {
        val uuid = view.tvMovieId.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }

    inner class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)

}