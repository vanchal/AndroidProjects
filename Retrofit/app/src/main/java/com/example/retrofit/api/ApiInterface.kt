package com.example.retrofit.api

import com.example.retrofit.models.SinglePageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/users")
    fun getSinglePage(@Query("page") page: Int?): Call<SinglePageResponse?>
}