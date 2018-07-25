package com.example.testmid.application

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class StarWarsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        // Initialize Fresco
        Fresco.initialize(this)
    }

}
