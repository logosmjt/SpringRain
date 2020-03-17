package com.jingtian.springrain.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface OperationApi {

    @GET("image/sogou/api.php")
    suspend fun getImage(@Query("type") type: String = "json"): ImageDataResponseBody
}