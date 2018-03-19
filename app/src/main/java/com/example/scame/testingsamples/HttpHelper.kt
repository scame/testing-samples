package com.example.scame.testingsamples

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

interface Api {

    @GET("posts/1")
    fun getPosts(): Call<PostModel>

    @GET("posts/1")
    fun getPostsRx(): Observable<PostModel>
}

class HttpHelper {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        val client: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}