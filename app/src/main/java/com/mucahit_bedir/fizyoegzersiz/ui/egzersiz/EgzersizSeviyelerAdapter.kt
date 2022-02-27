package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemEgzersizSeviyeBinding
import com.mucahit_bedir.fizyoegzersiz.util.GenericDiffUtil

class EgzersizSeviyelerAdapter(private val listAdapterListener: (EgzersizListeResponse.ItemEgzersizler) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<EgzersizListeResponse.ItemEgzersizler, EgzersizSeviyelerAdapter.EgzersizSeviyelerViewHolder>(
        GenericDiffUtil<EgzersizListeResponse.ItemEgzersizler>()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EgzersizSeviyelerViewHolder {
        return EgzersizSeviyelerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EgzersizSeviyelerViewHolder, position: Int) {
        holder.bind(getItem(position),listAdapterListener)
    }

    class EgzersizSeviyelerViewHolder(private val binding: ItemEgzersizSeviyeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: EgzersizListeResponse.ItemEgzersizler,
            listAdapterListener: (EgzersizListeResponse.ItemEgzersizler) -> Unit
        ) {
            with(item) {
                binding.titleTextView.text = name
                binding.aciklamaTextView.text = aciklama
                binding.countTextView.text = "${videolar.size} egzersiz"
            }
            binding.cardviewButton.setOnClickListener {
                listAdapterListener(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): EgzersizSeviyelerViewHolder {
                val binding = ItemEgzersizSeviyeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return EgzersizSeviyelerViewHolder(binding)
            }
        }
    }
}