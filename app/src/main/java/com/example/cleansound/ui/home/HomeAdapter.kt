package com.example.cleansound.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleansound.Model.ItemsItem
import com.example.cleansound.Model.SongModel

import com.example.cleansound.databinding.ItemListMusicBinding

class HomeAdapter (
    initialTracks: List<SongModel>
): RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    private var tracks: MutableList<SongModel> = initialTracks.toMutableList()
     inner class MyViewHolder(private val binding: ItemListMusicBinding): RecyclerView.ViewHolder(binding.root){
        fun bind( song: SongModel){
            binding.tvTitle.text = song.songName
            binding.tvArtist.text = song.artistName

            Glide.with(binding.root)
                .load(song.urlImage)
                .into(binding.icMusic)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newTracks: List<SongModel>){
        tracks.clear()
        tracks.addAll(newTracks)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemListMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trackMusic = tracks[position]
        holder.bind(trackMusic)
    }



}