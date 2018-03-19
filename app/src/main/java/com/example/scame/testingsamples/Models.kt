package com.example.scame.testingsamples

import com.google.gson.annotations.Expose
import retrofit2.Response

data class PostModel(@Expose val userId: Int,
                @Expose val id: Int,
                @Expose val title: String,
                @Expose val body: String)

data class PostModelResponse(val postModelResponse: Response<PostModel>)

data class FileDownloadedEvent(val randomNumber: Int)