package com.example.bn_android_assignment.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.example.bn_android_assignment.R
import com.example.bn_android_assignment.databinding.ItemCardsBinding
import com.example.bn_android_assignment.pojo.Card
import com.example.bn_android_assignment.util.TODO_MENU
import com.example.bn_android_assignment.util.changeBackgroundColor
import com.example.bn_android_assignment.util.show
import com.example.bn_android_assignment.util.showToast


class CardAdapter(private val context : Context, private val viewModel : CardsViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val cardsList = ArrayList<Card>()

    override fun getItemCount(): Int {
        return cardsList.size
    }

    inner class CardViewHolder(val binding: ItemCardsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as CardViewHolder){
            with(cardsList[position]) {

                this.data?.main_post?.let {

                    binding.itemUpperHalf.tvTitle.text = it.title
                    binding.itemUpperHalf.tvTitle.setTextColor(getProfileColor(position))

                    binding.itemUpperHalf.tvSubtitle.text = viewModel.getSubInfoStr(it.sub_info)
                    binding.itemUpperHalf.tvHeading.text = it.info

                    binding.itemUpperHalf.tvCounter.show(it.match_count?:0 > 0)
                    binding.itemUpperHalf.tvCounter.text = it.match_count.toString()
                }

                binding.itemUpperHalf.ivProfile.setImageDrawable(context.getDrawable(R.drawable.user))
                binding.itemUpperHalf.ivProfile.changeBackgroundColor(getProfileColor(position))

                binding.itemUpperHalf.tvAction.setOnClickListener {
                    context.showToast(TODO_MENU)
                }

                val snapHelper: SnapHelper = object : PagerSnapHelper() {
                    override fun findTargetSnapPosition(layoutManager: LayoutManager, velocityX: Int, velocityY: Int): Int {
                        val snapPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
                        binding.pageIndicatorView.selection = snapPosition

                        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
                    }
                }
                snapHelper.attachToRecyclerView(binding.rvSubCards)

                val adapter =  SubCardsAdapter(context, viewModel)
                adapter.setData(this.data?.horizontal_cards)

                binding.pageIndicatorView.count = this.data?.horizontal_cards?.size ?: 0

                binding.rvSubCards.adapter = adapter

            }
        }
    }

    private fun getProfileColor(position: Int): Int {
        return  context.getColor(viewModel.list[position % viewModel.list.size])
    }

    fun setData(cardsList: ArrayList<Card>?) {
        this.cardsList.clear()
        this.cardsList.addAll(cardsList ?: ArrayList())
        notifyDataSetChanged()
    }


}


