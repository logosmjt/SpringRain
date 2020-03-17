package com.jingtian.springrain.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkService {
    private val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().callTimeout(5, TimeUnit.SECONDS).build())
        .baseUrl("https://api.ooopn.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val operationApi = retrofit.create<OperationApi>()
}