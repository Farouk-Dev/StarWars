package com.example.testmid.activities.list_movies_activity.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.testmid.R
import com.example.testmid.activities.list_movies_activity.interfaces.ViewInterface
import com.example.testmid.activities.list_movies_activity.interfaces.WsCallInterface
import com.example.testmid.activities.list_movies_activity.presenter.ListMoviesActivityPresenter
import com.example.testmid.activities.movieDescriptionActivity.view.MovieDetailsActivity
import com.example.testmid.adapters.MovieAdapter
import com.example.testmid.utils.VerticalSpaceItemDecorator
import com.example.testmid.models.Movie
import com.example.testmid.utils.Constants
import com.example.testmid.utils.DeviceUtilis
import com.example.testmid.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_list_movies.*

class ListMoviesActivity : AppCompatActivity(), ViewInterface, WsCallInterface {

    // variable declarations **************************
    private lateinit var presenter: ListMoviesActivityPresenter
    private lateinit var adapter: MovieAdapter
    private lateinit var moviesDefaultSorting: ArrayList<Movie>
    private lateinit var movies: ArrayList<Movie>
    // ******************************************************
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        setSupportActionBar(toolbar)
        // initialize  the presenter
        presenter = ListMoviesActivityPresenter(this, this,this)
        // call the ws
        presenter.getMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        when (id) {
            R.id.action_sort_by_title -> {
                // sort by title
                adapter.update(presenter.sortByTitle(movies))
            }
            R.id.action_sort_by_year -> {
                // sort by year
                adapter.update(presenter.sortByYear(movies))
            }
            R.id.action_clear_sorting -> {
                // clear sorting
                adapter.update(moviesDefaultSorting)
            }
        }
        return true
    }


    // ViewInterface  interface  callbacks ************************
    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE

    }

    //********************************************************

    override fun onSuccess(response: ArrayList<Movie>) {

        response.get(0).images!!.clear()
        response.get(0).images!!.add("http://www.gstatic.com/webp/gallery/2.webp")
        response.get(0).images!!.add("http://www.gstatic.com/webp/gallery/5.jpg")
        response.get(0).images!!.add("http://www.gstatic.com/webp/gallery/4.webp")
        // initialize arrays
        movies = ArrayList()
        moviesDefaultSorting = ArrayList()
        // populate arrays
        presenter.populateMoviesArrays(response, movies, moviesDefaultSorting)

        //  Initialize ItemAnimator, LayoutManager and ItemDecorators
        val layoutManager = LinearLayoutManager(this)
        val itemDecorator = VerticalSpaceItemDecorator(20)
        //  Set the LayoutManager
        recyclerView.setLayoutManager(layoutManager)

        //  Set the ItemDecorator
        recyclerView.addItemDecoration(itemDecorator)
        adapter = MovieAdapter(movies, this) { movie: Movie -> partItemClicked(movie) }
        recyclerView.adapter = adapter
    }

    override fun onFailure(response: ArrayList<Movie>?) {

        if (!DeviceUtilis.isDeviceConnectedToInternet(this)) {
            // show popup
            DialogUtils.showDialog(this, getString(R.string.no_internet_connexion), getString(R.string.ok))
        }else{
        // show popup
        DialogUtils.showDialog(this, getString(R.string.problem_occured), getString(R.string.ok))}
    }

    /*  fonction to handle  recyclerView items click */
    private fun partItemClicked(movie: Movie) {
        // open the next activity
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.MOVIE, movie)
        startActivity(intent)
    }

}
