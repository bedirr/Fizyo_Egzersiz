package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemEgzersizListeBinding
import com.mucahit_bedir.fizyoegzersiz.util.GenericDiffUtil

class EgzersizListeAdapter(private val listAdapterListener: (EgzersizListeResponse) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<EgzersizListeResponse, EgzersizListeAdapter.EgzersizListeViewHolder>(
        GenericDiffUtil<EgzersizListeResponse>()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EgzersizListeViewHolder {
        return EgzersizListeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EgzersizListeViewHolder, position: Int) {
        holder.bind(getItem(position), listAdapterListener)
    }

    class EgzersizListeViewHolder(private val binding: ItemEgzersizListeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: EgzersizListeResponse,
            listAdapterListener: (EgzersizListeResponse) -> Unit
        ) {try {

            with(item) {
                Glide.with(binding.imageview).load(imageURL).into(binding.imageview)
                binding.titleTextview.text = name
            }
            }catch (e:Exception){}
            binding.cardviewButton.setOnClickListener {
                listAdapterListener(item)
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