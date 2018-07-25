package com.example.testmid.network

import com.example.testmid.models.Movie

import java.lang.reflect.Array
import java.util.ArrayList

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @get:GET("5927731b11000034066ccc7a")
    val stations: Call<ArrayList<Movie>>

}