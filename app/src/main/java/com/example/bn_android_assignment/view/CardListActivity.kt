package com.example.bn_android_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.bn_android_assignment.databinding.ActivityMainBinding
import com.example.bn_android_assignment.pojo.Card
import com.example.bn_android_assignment.util.NetworkResource
import com.example.bn_android_assignment.util.TAG

class CardListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(CardsViewModel::class.java)
    }

    private val adapter by lazy {
        CardAdapter(this, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvCards.adapter = adapter

        viewModel.getCards().observe(this) {

            when (it.status) {
                NetworkResource.Status.SUCCESS -> {
                    Log.d(TAG, "list count : ${it.data?.size}")
                    adapter.setData(it.data as? ArrayList<Card>?)
                }
                NetworkResource.Status.ERROR -> {
                    Log.d(TAG, "error : ${it.errorMsg}")
                }
                NetworkResource.Status.LOADING -> {
                    Log.d(TAG, "Loading...")
                }
            }

        }

    }
}