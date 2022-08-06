package com.example.bn_android_assignment.view

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bn_android_assignment.R
import com.example.bn_android_assignment.databinding.ItemSubCardsBinding
import com.example.bn_android_assignment.pojo.HorizontalCard
import com.example.bn_android_assignment.util.*


class SubCardsAdapter(private val context : Context, private val viewModel : CardsViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val subCardsList = ArrayList<HorizontalCard>()

    override fun getItemCount(): Int {
        return subCardsList.size
    }

    inner class SubCardViewHolder(val binding: ItemSubCardsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCardViewHolder {
        val binding = ItemSubCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as SubCardViewHolder){
            with(subCardsList[position]) {

                val displayWidth = Resources.getSystem().displayMetrics.widthPixels
                binding.parentLayout.layoutParams.width = displayWidth - 16.toPx() * 4

                binding.itemUpperHalfSubCard.tvAction.hide()

                binding.itemUpperHalfSubCard.tvTitle.setTextColor(context.getColor(R.color.background))
                binding.itemUpperHalfSubCard.tvTitle.setTextColor(context.getColor(R.color.white))
                binding.itemUpperHalfSubCard.tvSubtitle.setTextColor(context.getColor(R.color.white))
                binding.itemUpperHalfSubCard.tvHeading.setTextColor(context.getColor(R.color.white))

                binding.itemUpperHalfSubCard.ivProfile.setImageDrawable(context.getDrawable(R.drawable.home))
                binding.itemUpperHalfSubCard.tvTitle.text = this.title
                binding.itemUpperHalfSubCard.tvSubtitle.text = viewModel.getSubInfoStr(this.sub_info)
                binding.itemUpperHalfSubCard.tvHeading.text = this.info

                binding.tvBrokerName.text = this.assigned_to?.name
                binding.tvBrokerType.text = this.assigned_to?.org_name

                binding.ivCross.setOnClickListener {
                    context.showToast(TODO_REMOVE)
                }

                binding.ivCall.setOnClickListener {
                    context.showToast(TODO_CALL)
                }

                binding.ivSend.setOnClickListener {
                    context.showToast(TODO_SEND)
                }
            }
        }
    }

    fun setData(cardsList: List<HorizontalCard>?) {
        this.subCardsList.clear()
        this.subCardsList.addAll(cardsList ?: ArrayList())
        notifyDataSetChanged()
    }


}