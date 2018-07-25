package com.example.testmid.activities.list_movies_activity.presenter

import android.content.Context
import com.example.testmid.models.Movie
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ListMoviesActivityPresenterTest {



    lateinit  var movie1 : Movie
    lateinit  var movie2 : Movie
    lateinit  var movie3 : Movie
    lateinit  var movie4 : Movie
    lateinit  var  movies:ArrayList<Movie>


    @Before
    fun setUp() {
        movie1= Movie(ArrayList(arrayListOf("image1","image2")),"xmen","intro","2000","text")
        movie2= Movie(ArrayList(arrayListOf("image1","image2")),"batman","intro","2014","text")
        movie3= Movie(ArrayList(arrayListOf("image1","image2")),"spiderman","intro","2005","text")
        movie4= Movie(ArrayList(arrayListOf("image1","image2")),"twilight","intro","2018","text")
        movies= ArrayList(arrayListOf(movie1,movie2,movie3,movie4))

    }

    @Test
    fun sortByTitle() {
    val sortedListTitle =ListMoviesActivityPresenter().sortByTitle(movies )
        Assert.assertEquals("batman",sortedListTitle.get(0).title)
        Assert.assertEquals("spiderman",sortedListTitle.get(1).title)
        Assert.assertEquals("twilight",sortedListTitle.get(2).title)
        Assert.assertEquals("xmen",sortedListTitle.get(3).title)

    }

    @Test
    fun sortByYear() {
        val sortedListYear =ListMoviesActivityPresenter().sortByYear(movies )
        Assert.assertEquals("2000",sortedListYear.get(0).year)
        Assert.assertEquals("2005",sortedListYear.get(1).year)
        Assert.assertEquals("2014",sortedListYear.get(2).year)
        Assert.assertEquals("2018",sortedListYear.get(3).year)

    }

}