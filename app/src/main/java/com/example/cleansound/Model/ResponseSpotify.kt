package com.example.cleansound.Model

import com.google.gson.annotations.SerializedName

data class ResponseSpotify(

	@field:SerializedName("tracks")
	val tracks: Tracks? = null
)

data class Album(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null
)

data class ItemsItem(

	@field:SerializedName("track")
	val track: Track? = null
)

data class ImagesItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Track(

	@field:SerializedName("artists")
	val artists: List<ArtistsItem?>? = null,

	@field:SerializedName("album")
	val album: Album? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class ArtistsItem(

	@field:SerializedName("name")
	val name: String? = null
)

data class Tracks(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)
