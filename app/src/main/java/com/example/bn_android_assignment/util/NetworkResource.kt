package com.example.bn_android_assignment.util

class NetworkResource<T>(val status: Status, val data: T?, val errorMsg: String?) {

    enum class Status{
        SUCCESS, LOADING, ERROR
    }

    companion object{

        fun <T> success(data: T? = null): NetworkResource<T> {
          return NetworkResource(Status.SUCCESS, data, null)
        }

        fun <T> loading(data: T? = null): NetworkResource<T> {
          return NetworkResource(Status.LOADING, data, null)
        }

        fun <T> error(msg: String, data: T? = null): NetworkResource<T> {
          return NetworkResource(Status.ERROR, data, msg)
        }
    }
}