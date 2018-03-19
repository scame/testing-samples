package com.example.scame.testingsamples.samples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.test.espresso.idling.CountingIdlingResource
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.testingsamples.Api
import com.example.scame.testingsamples.HttpHelper
import com.example.scame.testingsamples.PostModel
import com.example.scame.testingsamples.R
import retrofit2.Callback
import retrofit2.Response

class HandlerSample : AppCompatActivity() {

    @BindView(R.id.img)
    lateinit var img: ImageView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    val idlingResource = CountingIdlingResource(HandlerSample::class.java.canonicalName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handler_sample_activity)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.button)
    fun onButtonClick() {
        progressBar.visibility = View.VISIBLE

        idlingResource.increment()

        Handler().postDelayed({
            img.visibility = if (img.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            progressBar.visibility = View.GONE


            idlingResource.decrement()

        }, 3000)
    }
}
