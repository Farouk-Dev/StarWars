package com.example.testmid.activities.list_movies_activity.presenter

import android.content.Context
import android.util.Log
import com.example.testmid.R
import com.example.testmid.activities.list_movies_activity.interfaces.ViewInterface
import com.example.testmid.activities.list_movies_activity.interfaces.WsCallInterface
import com.example.testmid.models.Movie
import com.example.testmid.network.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class ListMoviesActivityPresenter() {
    private lateinit  var context:Context
    private lateinit  var viewInterface:ViewInterface
    private lateinit  var wsCallInterface:WsCallInterface

    constructor(  context: Context,  viewInterface: ViewInterface,   wsCallInterface: WsCallInterface) : this(){
        this.context=context
        this.viewInterface=viewInterface
        this.wsCallInterface=wsCallInterface
    }

    /*  populate arrays*/
    fun populateMoviesArrays(response: ArrayList<Movie>,movies:ArrayList<Movie>,moviesDefaultSorting:ArrayList<Movie>){
        response.forEach { movie ->
            movies.add(movie)
            moviesDefaultSorting.add(movie)
        }

    }

    /*   sorting movies by title*/
    fun sortByTitle(movies: ArrayList<Movie>): ArrayList<Movie> {
        val sortedList = movies.sortedBy { it.title }
        return ArrayList<Movie>(sortedList)
    }


    /*   sorting movies by title*/
    fun sortByYear(movies: ArrayList<Movie>): ArrayList<Movie> {
        val sortedList = movies.sortedBy { it.year }
        return ArrayList<Movie>(sortedList)
    }

    /* web service call*/
    fun getMovies() {
        viewInterface.showProgressBar()
        RetrofitManager.getInstance(context.getString(R.string.base_url)).service!!.stations.enqueue(object : Callback<ArrayList<Movie>> {
            override fun onResponse(call: Call<ArrayList<Movie>>, response: Response<ArrayList<Movie>>) {
                viewInterface.hideProgressBar()
                wsCallInterface.onSuccess(response.body() as java.util.ArrayList<Movie>)
            }

            override fun onFailure(call: Call<ArrayList<Movie>>, t: Throwable) {
                viewInterface.hideProgressBar()
                wsCallInterface.onFailure(null)
            }
        })
    }
}
