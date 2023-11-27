package com.example.cleansound.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleansound.Model.ItemsItem
import com.example.cleansound.Model.SongModel
import com.example.cleansound.Model.Track
import com.example.cleansound.Model.Tracks
import com.example.cleansound.databinding.ItemListMusicBinding

class HomeAdapter (
    private val context: Context,
    private val tracks: List<SongModel>
): RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    inner class MyViewHolder(private val binding: ItemListMusicBinding): RecyclerView.ViewHolder(binding.root){
        fun bind( song: SongModel){
            binding.tvTitle.text = song.songName
            binding.tvArtist.text = song.artistName

            Glide.with(context)
                .load(song.urlImage)
                .into(binding.icMusic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemListMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val track = tracks[position]
        holder.bind(track)
    }
}