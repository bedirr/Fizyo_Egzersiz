package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemEgzersizVideolarBinding
import com.mucahit_bedir.fizyoegzersiz.util.GenericDiffUtil

class EgzersizVideolarAdapter(private val listAdapterListener: (EgzersizListeResponse.ItemEgzersizler.EgzersizVideo) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<EgzersizListeResponse.ItemEgzersizler.EgzersizVideo, EgzersizVideolarAdapter.EgzersizVideolarViewHolder>(
        GenericDiffUtil<EgzersizListeResponse.ItemEgzersizler.EgzersizVideo>()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EgzersizVideolarViewHolder {
        return EgzersizVideolarViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EgzersizVideolarViewHolder, position: Int) {
        holder.bind(getItem(position), listAdapterListener)
    }

    class EgzersizVideolarViewHolder(private val binding: ItemEgzersizVideolarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(
            item: EgzersizListeResponse.ItemEgzersizler.EgzersizVideo,
            listAdapterListener: (EgzersizListeResponse.ItemEgzersizler.EgzersizVideo) -> Unit
        ) {
            with(item) {
                binding.videoAciklamaTextview.text = name
            }
            binding.videoExoplayerView.setOnErrorListener { mediaPlayer, i, i2 ->
                    true
            }
            binding.videoExoplayerView.setOnClickListener {
                listAdapterListener(item)
                val uri: Uri = Uri.parse(item.videoURL)
                try {

                    binding.videoExoplayerView.setMediaController(MediaController(binding.root.context))
                } catch (e: Exception) {
                    /* bu hata veriyor sürekli başka bir video oynatıcı bul burada çalıştırmaya çalış ben dışarı çıkcam akşam gelirim ben yokken işlerini tamamla*/
                }
                binding.videoExoplayerView.setVideoURI(uri)
                binding.videoExoplayerView.requestFocus()
                binding.videoExoplayerView.start()
                /*
                tamamdır gerisi sende
                senimi beklicem giriş yap
                 */
            }
        }

        companion object {
            fun from(parent: ViewGroup): EgzersizVideolarViewHolder {
                val binding = ItemEgzersizVideolarBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return EgzersizVideolarViewHolder(binding)
            }
        }
    }
}