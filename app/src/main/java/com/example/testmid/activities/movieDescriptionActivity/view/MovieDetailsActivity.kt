package com.example.testmid.activities.movieDescriptionActivity.view

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.testmid.R
import com.example.testmid.activities.movieDescriptionActivity.presenter.MovieDetailsActivityPresenter
import com.example.testmid.adapters.SlidingPictureAdapter
import com.example.testmid.models.Movie
import com.example.testmid.utils.Constants
import com.example.testmid.utils.CustomScroller
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.lang.reflect.Field
import com.example.testmid.utils.MoviePageTransformer


class MovieDetailsActivity : AppCompatActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {

    // variable declarations **************************
    private lateinit var presenter: MovieDetailsActivityPresenter
    private lateinit var movie: Movie
    private lateinit var pictures: ArrayList<String>
    private var timer = Handler()
    private var currentPicture = 0
    private lateinit var mScroller: Field
    //*************************************************
    override fun onClick(p0: View?) {
        // go back
        onBackPressed()

    }

    private lateinit var runnableCode: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        this.setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // initialize  the presenter
        presenter = MovieDetailsActivityPresenter()
        // show movie's details
        showMovieDetails()
        //add views  listners
        toolbar.setNavigationOnClickListener(this)
        mPager.addOnPageChangeListener(this)
    }

    // addOnPageChangeListener callbacks *************************
    override fun onPageScrollStateChanged(p0: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(position: Int) {
        currentPicture = position

    }
    //****************************************************************

    /*show movie's details*/
    private fun showMovieDetails() {

        // get the intent bundle
        val bundle = intent.extras
        movie = bundle.getParcelable(Constants.MOVIE)
        // set toolbar title
        supportActionBar?.title = movie.title + " (" + movie.year + ")"
        txt_intro.text = movie.intro
        txt_text.text = movie.text
        // initialize pictures
        pictures = ArrayList()
        //populate the pictures array
        presenter.populateList(pictures, movie)
        mScroller = ViewPager::class.java.getDeclaredField("mScroller")
        mScroller.isAccessible = true
        mPager.adapter = SlidingPictureAdapter(this, pictures)
        // call function
        synchronizeTime()

    }

    /* show the next picture into the  viewPager*/
    private fun showNextPicture() {
        if (currentPicture + 1 == pictures.size) {
            mScroller.set(mPager, CustomScroller(this, FastOutSlowInInterpolator(), 10))
            mPager.setPageTransformer(true, null)
            mPager.currentItem = 0
        } else {
            mScroller.set(mPager, CustomScroller(this, FastOutSlowInInterpolator(), 19000))
            mPager.setPageTransformer(true, MoviePageTransformer())
            mPager.currentItem = currentPicture + 1
        }
    }

    /*
    sychronise between pictures translation time and the needed time to pause each picture
    */
    private fun synchronizeTime() {
        runnableCode = Runnable {
            showNextPicture()
            timer.postDelayed(runnableCode, 16000)
            if (currentPicture == 0) {
                timer.removeCallbacksAndMessages(null)
                timer.postDelayed(runnableCode, 100)
            }
        }
        timer.postDelayed(runnableCode, 1000)
    }
}
