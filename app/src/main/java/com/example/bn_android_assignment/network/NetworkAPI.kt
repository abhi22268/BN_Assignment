package com.example.bn_android_assignment.network


import com.example.bn_android_assignment.pojo.CardsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {

    @GET("c52cf4ce-a639-42d7-a606-2c0a8b848536")
    suspend fun getCards() : Response<CardsResponse>

}