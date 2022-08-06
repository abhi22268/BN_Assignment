package com.example.bn_android_assignment.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Response

abstract class NetworkHelper<RequiredObj, RequestObj> {

    private val results = MutableLiveData<NetworkResource<RequiredObj>>()

    init {
        init()
    }

    private fun init(){

        Log.d(TAG, "loading call")
        results.postValue(NetworkResource.loading())
        fetchDataFromNetwork()
    }

    private fun fetchDataFromNetwork() {
        Log.d(TAG, "network call")
        val response = makeNetworkCall()
        if(response?.isSuccessful == true && response.body() != null){
            Log.d(TAG, "network call received success")
            results.postValue(getRequiredObj(response.body()!!))
        }else{
            Log.d(TAG, "network call received error")
            results.postValue(NetworkResource.error(response?.message() ?: "ERROR"))
        }
    }

    abstract fun makeNetworkCall(): Response<RequestObj>?

    abstract fun getRequiredObj(body: RequestObj): NetworkResource<RequiredObj>?

    fun getLiveData(): MutableLiveData<NetworkResource<RequiredObj>> {
        return results
    }
}