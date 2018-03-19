package com.example.scame.testingsamples.samples

import android.content.Intent
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


class ServiceLongActionSample: AppCompatActivity() {

    @BindView(R.id.textView)
    lateinit var textView: TextView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    val idlingResource = CountingIdlingResource(ServiceLongActionSample::class.java.canonicalName)

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

        startService(Intent(this, FileDownloadService::class.java))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFileDownloadedEvent(fileDownloadedEvent: FileDownloadedEvent) {
        textView.text = fileDownloadedEvent.randomNumber.toString()

        progressBar.visibility = View.GONE
        idlingResource.decrement()
    }
}