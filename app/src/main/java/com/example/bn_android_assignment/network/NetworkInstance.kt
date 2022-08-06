package com.example.bn_android_assignment.network

import com.example.bn_android_assignment.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInstance {

    val networkApi : NetworkAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkAPI::class.java)
    }
}