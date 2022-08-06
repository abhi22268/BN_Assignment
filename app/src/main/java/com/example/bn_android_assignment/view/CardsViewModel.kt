package com.example.bn_android_assignment.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bn_android_assignment.R
import com.example.bn_android_assignment.pojo.Card
import com.example.bn_android_assignment.pojo.SubInfo
import com.example.bn_android_assignment.repositories.NetworkRepo
import com.example.bn_android_assignment.util.NetworkResource


class CardsViewModel(application: Application) : AndroidViewModel(application) {

    val list = arrayListOf(R.color.profileColor1, R.color.profileColor2)

    fun getCards() : MutableLiveData<NetworkResource<List<Card>>> {
        return NetworkRepo.getCards()
    }

    fun getSubInfoStr(subInfo: List<SubInfo>?): String? {
        return subInfo?.map{ it.text }?.joinToString(" \u25CF ")
    }

}