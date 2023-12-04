package com.example.cleansound.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleansound.Auth.MyApplication
import com.example.cleansound.Model.ItemsItem
import com.example.cleansound.Model.ItemsItemMusic
import com.example.cleansound.Model.SongModel
import com.example.cleansound.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeadapter: HomeAdapter
    private lateinit var accessToken: String
    private lateinit var spotifyViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spotifyViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        (requireActivity().application as MyApplication).accessToken.toString()
            .also { accessToken = it }
        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.VERTICAL
        homeadapter = HomeAdapter(emptyList())
        val rvMusic = binding.rvMusic
        val searchView = binding.srMusic
        rvMusic.setHasFixedSize(true)
        rvMusic.layoutManager = lm
        rvMusic.adapter = homeadapter

        spotifyViewModel.tracks.observe(viewLifecycleOwner) {
            displayPlayListMusic(it)
        }
        spotifyViewModel.getPlayListMusic("7KHJBz12xG3fPKErBd41K9", accessToken )

        spotifyViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        spotifyViewModel.isSearchMode.observe(viewLifecycleOwner) { isSearchMode ->
            if (isSearchMode) {
                spotifyViewModel.searchTracks.observe(viewLifecycleOwner) {
                    displaySearhMusic(it)
                }
            } else {
                spotifyViewModel.tracks.observe(viewLifecycleOwner) {
                    displayPlayListMusic(it)
                }
                spotifyViewModel.getPlayListMusic("7KHJBz12xG3fPKErBd41K9", accessToken)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                Handler(Looper.getMainLooper()).postDelayed({
                    if(!newText.isNullOrEmpty()) {
                        spotifyViewModel.setSearchMode(true)
                        spotifyViewModel.getSeacrhMusic(newText, accessToken)
                    } else {
                        spotifyViewModel.setSearchMode(false)
                    }
                }, 500)
                return false
            }
        })
    }

    private fun displayPlayListMusic(music: List<ItemsItem>){
        val songList = music.map {
            SongModel(
                artistName = it?.track?.artists?.firstOrNull()?.name,
                songName = it?.track?.name,
                urlImage = it?.track?.album?.images?.firstOrNull()?.url
            )
        }
        homeadapter.updateData(songList)
    }
    private fun displaySearhMusic(music: List<ItemsItemMusic>){
        val songList = music.map {
            SongModel(
                artistName = it?.artists?.firstOrNull()?.name,
                songName = it?.name,
                urlImage = it?.album?.images?.firstOrNull()?.url
            )
        }
        homeadapter.updateData(songList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



