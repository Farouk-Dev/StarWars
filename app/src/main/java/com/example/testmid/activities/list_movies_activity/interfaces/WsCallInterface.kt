package com.example.testmid.activities.list_movies_activity.interfaces

import com.example.testmid.models.Movie

import java.util.ArrayList

interface WsCallInterface  {
    // callback when get ws success response
    fun onSuccess(response: ArrayList<Movie>)

    // callback when get ws failed response
    fun onFailure(response: ArrayList<Movie>?)
}
