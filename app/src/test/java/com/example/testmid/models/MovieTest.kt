package com.example.testmid.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieTest {


    private
    lateinit  var movie : Movie
    @Before
    fun setUp() {
        movie= Movie(ArrayList(arrayListOf("image1","image2")),"title","intro","year","text")
    }

    @Test
    fun getImages() {
        Assert.assertEquals(ArrayList(arrayListOf("image1","image2")),movie.images)
    }

    @Test
    fun getTitle() {
        Assert.assertEquals("title",movie.title)
    }

    @Test
    fun getIntro() {
        Assert.assertEquals("intro",movie.intro)
    }

    @Test
    fun getYear() {
        Assert.assertEquals("year",movie.year)
    }

    @Test
    fun getText() {
        Assert.assertEquals("text",movie.text)

    }
}