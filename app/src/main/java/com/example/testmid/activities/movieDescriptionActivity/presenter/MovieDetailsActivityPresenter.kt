package com.example.testmid.activities.movieDescriptionActivity.presenter

import android.content.Context
import android.widget.Toast
import com.example.testmid.models.Movie

class MovieDetailsActivityPresenter() {
    fun populateList( pictures:ArrayList<String>,movie:Movie){
        movie.images!!.forEach { picture -> pictures.add(picture) }
    }

}
