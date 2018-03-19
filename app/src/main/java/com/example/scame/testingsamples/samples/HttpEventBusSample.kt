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
import com.example.scame.testingsamples.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class HttpEventBusSample: AppCompatActivity() {

    @BindView(R.id.textView)
    lateinit var textView: TextView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    val idlingResource = CountingIdlingResource(HttpEventBusSample::class.java.canonicalName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_sample_activity)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @OnClick(R.id.button)
    fun onButtonClick() {
        idlingResource.increment()
        progressBar.visibility = View.VISIBLE

        Thread(Runnable {
            val syncCall = HttpHelper.client.create(Api::class.java).getPosts()
            val response = syncCall.execute()

            EventBus.getDefault().post(PostModelResponse(response))

        }).start()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPostEvent(postEvent: PostModelResponse) {
        val response = postEvent.postModelResponse

        if (response.isSuccessful) {
             textView.text = response.body()?.title
        } else {
            Log.i(HttpEventBusSample::class.java.canonicalName, response.errorBody().toString())
        }

        progressBar.visibility = View.GONE
        idlingResource.decrement()
    }
}