package com.example.cleansound.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleansound.Auth.MyApplication
import com.example.cleansound.Model.ItemsItem
import com.example.cleansound.Model.ResponseSpotify
import com.example.cleansound.Model.SongModel
import com.example.cleansound.Model.Track
import com.example.cleansound.databinding.FragmentHomeBinding
import com.example.cleansound.network.SpotifyApiConfig
import com.example.cleansound.network.SpotifyApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: HomeAdapter
    private lateinit var ArrayMusicList: ArrayList<Track>
    private lateinit var spotifyApiServices: SpotifyApiServices
    private lateinit var accessToken: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        spotifyApiServices = SpotifyApiConfig.getSpotifyApiServices()
        getTokenAndMakeApiCall()
        return root
    }

    private fun getTokenAndMakeApiCall(){
        GlobalScope.launch(Dispatchers.Main){
            accessToken = (requireActivity().application as MyApplication).accessToken.toString()

            fetchDataFromSpotify()
        }
    }
    private fun fetchDataFromSpotify(){
        GlobalScope.launch(Dispatchers.Main){
            val response: Response<ResponseSpotify> = spotifyApiServices.getSpotifyData("Bearer ${accessToken}")

            if(response.isSuccessful){
                val data: ResponseSpotify? = response.body()

                data?.tracks?.items?.let {
                    items -> displayDataInRecyclerView(items)
                }


            } else {
                val errorBody: ResponseBody? = response.errorBody()
                Toast.makeText(requireContext(),"Error: ${errorBody?.string()}", Toast.LENGTH_SHORT).show()
                Log.e("SpotifyFragment", "Error: ${errorBody?.string()} ")
            }
        }
    }

    private fun displayDataInRecyclerView(tracks: List<ItemsItem?>){
        val songList = tracks.map {
             SongModel(
                artistName = it?.track?.artists?.firstOrNull()?.name,
                 songName = it?.track?.name,
                 urlImage = it?.track?.album?.images?.firstOrNull()?.url
            )
        }

        val adapter = HomeAdapter(requireContext(), songList)
        val recyclerView = binding.rvMusic
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

