package com.example.scame.testingsamples.samples

import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.testingsamples.Api
import com.example.scame.testingsamples.HttpHelper
import com.example.scame.testingsamples.PostModel
import com.example.scame.testingsamples.R
import retrofit2.Callback
import retrofit2.Response

class HttpEnqueueSample: AppCompatActivity() {

    @BindView(R.id.textView)
    lateinit var textView: TextView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    val idlingResource = CountingIdlingResource(HttpEnqueueSample::class.java.canonicalName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_sample_activity)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.button)
    fun onButtonClick() {
        idlingResource.increment()
        progressBar.visibility = View.VISIBLE

        HttpHelper.client.create(Api::class.java).getPosts().enqueue(object : Callback<PostModel> {
            override fun onFailure(call: retrofit2.Call<PostModel>?, t: Throwable?) {
                Log.i(HttpEnqueueSample::class.java.canonicalName, t?.toString())
                progressBar.visibility = View.GONE
                idlingResource.decrement()
            }

            override fun onResponse(call: retrofit2.Call<PostModel>?, response: Response<PostModel>?) {
                textView.text = response?.body()?.body
                progressBar.visibility = View.GONE
                idlingResource.decrement()
            }
        })
    }
}