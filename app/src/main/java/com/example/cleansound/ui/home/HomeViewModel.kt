package com.example.cleansound.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleansound.Model.ItemsItem
import com.example.cleansound.Model.ItemsItemMusic
import com.example.cleansound.network.SpotifyApiConfig
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _tracks = MutableLiveData<List<ItemsItem>>()
    val tracks: LiveData<List<ItemsItem>> get() = _tracks

    private val _searchTracks = MutableLiveData<List<ItemsItemMusic>>()
    val searchTracks: LiveData<List<ItemsItemMusic>> get() = _searchTracks

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isSearchMode = MutableLiveData<Boolean>()
    val isSearchMode: LiveData<Boolean> get() = _isSearchMode

    fun getPlayListMusic(playListId: String, token: String){
        viewModelScope.launch {
            try {
                val response = SpotifyApiConfig.getSpotifyApiServices().getSpotifyData(playListId, "Bearer $token")
                if (response.isSuccessful){
                    _tracks.value = response.body()?.tracks?.items as List<ItemsItem>

                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                    Log.e("HomeViewModel", "Message:  ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception){
                _error.value = "Exception: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getSeacrhMusic(music: String, token: String){
        viewModelScope.launch {
            try {
                val response = SpotifyApiConfig.getSpotifyApiServices().getSearchData(music, "Bearer $token")
                if (response.isSuccessful){
                    _searchTracks.value = response.body()?.tracks?.items as List<ItemsItemMusic>
                } else {
                    Log.e("HomeViewModel", "Message:  ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception){
                _error.value = "Exception: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }



    fun setSearchMode(isSearchMode: Boolean){
        _isSearchMode.value = isSearchMode
    }
}