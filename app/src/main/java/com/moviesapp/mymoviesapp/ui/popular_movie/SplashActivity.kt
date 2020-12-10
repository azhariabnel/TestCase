package com.moviesapp.mymoviesapp.ui.popular_movie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.moviesapp.mymoviesapp.BuildConfig
import com.moviesapp.mymoviesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setVersionCode()
        splashHandler()
    }

    private fun setVersionCode() {
        val versionName: String = BuildConfig.VERSION_NAME
        val versionCode: Int = BuildConfig.VERSION_CODE
        tvVersion.text = versionName
    }

    private fun splashHandler() {
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable {
                gotoIntro()
            }, 2000)
    }


    private fun gotoIntro(){
        Intent(this, IntroActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }
}