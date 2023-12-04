package com.example.cleansound.network

import com.example.cleansound.Model.ResponseMusic
import com.example.cleansound.Model.ResponseSpotify
import com.example.cleansound.Model.Track
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApiServices {
    @GET("v1/playlists/{endpoint}")
    suspend fun getSpotifyData(
        @Path("endpoint") endpoint: String,
        @Header("Authorization") token: String
    ): Response<ResponseSpotify>

    @GET("v1/search?type=track&market=ID")
    suspend fun getSearchData(
        @Query("q") query: String,
        @Header("Authorization") token: String
    ): Response<ResponseMusic>
}