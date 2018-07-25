package com.example.testmid.adapters

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testmid.R
import com.example.testmid.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val items: ArrayList<Movie>, val context: Context, val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieHolder>() {


    // Gets the number of status in the list
    override fun getItemCount(): Int {
        return items.size
    }


    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: MovieHolder, position: Int) = holder.bind(items[position], listener)


    fun update(items: ArrayList<Movie>?) {
        this.items.clear()
        items?.forEach { item -> this.items.add(item) }
        notifyDataSetChanged()
    }
}


class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
    val picture = view.picture
    val title = view.txt_title
    val year = view.txt_year
    fun bind(movie: Movie, listener: (Movie) -> Unit) = with(itemView) {
        //show the title
        title.text = movie.title
        //show the year
        year.text = movie.year
        //show the picture
        val pictureUri = movie.images!!.get(0)
        picture.setImageURI(pictureUri)
        // add movie's click listner
        setOnClickListener { listener(movie) }
    }
}
