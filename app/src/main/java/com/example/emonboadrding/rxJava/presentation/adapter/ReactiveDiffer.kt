package com.example.emonboadrding.rxJava.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.emonboadrding.rxJava.domain.DiscountCard

object ReactiveDiffer: DiffUtil.ItemCallback<DiscountCard>() {
    override fun areItemsTheSame(oldItem: DiscountCard, newItem: DiscountCard): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: DiscountCard, newItem: DiscountCard): Boolean {
        return oldItem.id == newItem.id
    }

}