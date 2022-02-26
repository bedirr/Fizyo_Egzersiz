package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemEgzersizListeBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemViewSelectDaysBinding
import com.mucahit_bedir.fizyoegzersiz.ui.calendar.CalendarDay
import com.mucahit_bedir.fizyoegzersiz.ui.calendar.CalendarDaysAdapter
import com.mucahit_bedir.fizyoegzersiz.util.GenericDiffUtil
import java.text.SimpleDateFormat

class EgzersizListeAdapter(private val listAdapterListener: (EgzersizListeResponse) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<EgzersizListeResponse, EgzersizListeAdapter.EgzersizListeViewHolder>(GenericDiffUtil<EgzersizListeResponse>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EgzersizListeViewHolder {
        return EgzersizListeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EgzersizListeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EgzersizListeViewHolder(private val binding: ItemEgzersizListeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EgzersizListeResponse) {
            with(item) {
                Glide.with(binding.imageview).load(imageURL).into(binding.imageview)
                binding.titleTextview.text = name
            }
        }

        companion object {
            fun from(parent: ViewGroup): EgzersizListeViewHolder {
                val binding = ItemEgzersizListeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return EgzersizListeViewHolder(binding)
            }
        }
    }
}