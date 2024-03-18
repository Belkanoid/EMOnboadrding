package com.example.emonboadrding.rxJava.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.emonboadrding.databinding.RecyclerListItemBinding
import com.example.emonboadrding.rxJava.domain.DiscountCard
import io.reactivex.subjects.PublishSubject

class ReactiveAdapter: ListAdapter<DiscountCard, ReactiveHolder>(ReactiveDiffer) {

    val onItemClickPublish = PublishSubject.create<DiscountCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReactiveHolder(
        RecyclerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ReactiveHolder, position: Int) {
        val discountCard = getItem(position)
        holder.onBind(discountCard, onItemClickPublish::onNext)
    }
}


class ReactiveHolder(private val binding: RecyclerListItemBinding): ViewHolder(binding.root) {
    fun onBind(discountCard: DiscountCard, onClick: (DiscountCard) -> Unit) {
        binding.textView3.text = discountCard.name
        binding.root.setOnClickListener{
            onClick.invoke(discountCard)
        }
    }
}

