package com.example.cleansound.network

import com.example.cleansound.Model.ResponseSpotify
import com.example.cleansound.Model.Track
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpotifyApiServices {
    @GET("v1/playlists/37i9dQZF1DXa2EiKmMLhFD?market=ID&fields=tracks.items%28track%28name%2Cartists%28name%29%2Calbum%28images%29%29%29")
    suspend fun getSpotifyData(
        @Header("Authorization") token: String
    ): Response<ResponseSpotify>
}