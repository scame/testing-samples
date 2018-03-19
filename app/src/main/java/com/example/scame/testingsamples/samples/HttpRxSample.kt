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
import com.example.scame.testingsamples.R
import io.reactivex.android.schedulers.AndroidSchedulers


class HttpRxSample: AppCompatActivity() {

    @BindView(R.id.textView)
    lateinit var textView: TextView
    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    val idlingResource = CountingIdlingResource(HttpRxSample::class.java.canonicalName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_sample_activity)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.button)
    fun onButtonClick() {
        HttpHelper.client.create(Api::class.java)
                .getPostsRx()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    idlingResource.increment();
                    progressBar.visibility = View.VISIBLE
                }
                .doFinally {
                    progressBar.visibility = View.GONE
                    idlingResource.decrement()
                }
                .subscribe(
                        { textView.text = it.title },
                        { Log.i(HttpRxSample::class.java.canonicalName, it.toString()) }
                )
    }
}