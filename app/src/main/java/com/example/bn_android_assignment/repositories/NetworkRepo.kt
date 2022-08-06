package com.example.bn_android_assignment.repositories

import androidx.lifecycle.MutableLiveData
import com.example.bn_android_assignment.network.NetworkInstance
import com.example.bn_android_assignment.pojo.Card
import com.example.bn_android_assignment.pojo.CardsResponse
import com.example.bn_android_assignment.util.EMPTY_LIST_ERROR
import com.example.bn_android_assignment.util.NetworkHelper
import com.example.bn_android_assignment.util.NetworkResource
import kotlinx.coroutines.*
import retrofit2.Response


object NetworkRepo {

    fun getCards() : MutableLiveData<NetworkResource<List<Card>>> {

        return object : NetworkHelper<List<Card>, CardsResponse>() {

            override fun makeNetworkCall(): Response<CardsResponse> = runBlocking {
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) { NetworkInstance.networkApi.getCards() }
            }

            override fun getRequiredObj(body: CardsResponse): NetworkResource<List<Card>> {
                return if(body.cards.isNullOrEmpty()){
                    NetworkResource.error(EMPTY_LIST_ERROR)
                }else{
                    NetworkResource.success(body.cards)
                }
            }
        }.getLiveData()
    }

}