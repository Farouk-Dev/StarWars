package com.example.testmid.activities.movieDescriptionActivity.presenter

import com.example.testmid.activities.list_movies_activity.presenter.ListMoviesActivityPresenter
import com.example.testmid.models.Movie
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MovieDetailsActivityPresenterTest {

    private lateinit var movie :Movie
    private lateinit var  pictures :ArrayList<String>
    @Before
    fun setUp() {
        movie= Movie(ArrayList(arrayListOf("image1","image2")),"xmen","intro","2000","text")
        pictures= ArrayList()
        MovieDetailsActivityPresenter().populateList(pictures,movie)
    }

    @Test
    fun populateList() {

        Assert.assertEquals("image1",pictures.get(0))
        Assert.assertEquals("image2",pictures.get(1))

    }
}