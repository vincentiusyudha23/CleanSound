package com.example.cleansound.ui.dashboard

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track

class DashboardViewModel : ViewModel() {

    private val clientId = "1a7f0f47d7cb45148c52b560d6459f78"
    private val redirectURI = "https://com.example.cleansound/callback"
    private var spotifyAppRemote: SpotifyAppRemote? = null
    private val _currentTrack = MutableLiveData<String>()
    val currenTrack: LiveData<String>
        get() = _currentTrack


    fun connectToSpotify(contex: Context) {
        val connectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectURI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(contex,connectionParams, object  : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                Log.d("DashBoardFragment", "Berhasil Menyambungkan")
                Toast.makeText(contex, "Berhasil", Toast.LENGTH_SHORT).show()
                connected()
            }

            override fun onFailure(throwable: Throwable) {
                Log.e("DashBoardFragment", throwable.message, throwable)
            }
        })
    }
    private fun connected(){
        spotifyAppRemote?.let {
            val playlistURI = "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL"
            it.playerApi.play(playlistURI)
            it.playerApi.subscribeToPlayerState().setEventCallback {
                val track: Track = it.track
                val trackInfo = "${track.name} by ${track.artist.name}"
                _currentTrack.postValue(trackInfo)
                Log.d("DashBoardFragment", track.name + " by " + track.artist.name)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        spotifyAppRemote?.let {
            SpotifyAppRemote.disconnect(it)
        }
    }
}