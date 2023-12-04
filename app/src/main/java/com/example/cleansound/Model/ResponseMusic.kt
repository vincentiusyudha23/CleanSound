package com.example.cleansound.Model

import com.google.gson.annotations.SerializedName

data class ResponseMusic(

	@field:SerializedName("tracks")
	val tracks: TracksMusic? = null
)

data class ImagesItemMusic(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class ExternalIdsMusic(

	@field:SerializedName("isrc")
	val isrc: String? = null
)

data class ItemsItemMusic(

	@field:SerializedName("disc_number")
	val discNumber: Int? = null,

	@field:SerializedName("album")
	val album: Album? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("external_ids")
	val externalIds: ExternalIds? = null,

	@field:SerializedName("uri")
	val uri: String? = null,

	@field:SerializedName("duration_ms")
	val durationMs: Int? = null,

	@field:SerializedName("explicit")
	val explicit: Boolean? = null,

	@field:SerializedName("is_playable")
	val isPlayable: Boolean? = null,

	@field:SerializedName("artists")
	val artists: List<ArtistsItem?>? = null,

	@field:SerializedName("preview_url")
	val previewUrl: String? = null,

	@field:SerializedName("popularity")
	val popularity: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("track_number")
	val trackNumber: Int? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("is_local")
	val isLocal: Boolean? = null,

	@field:SerializedName("external_urls")
	val externalUrls: ExternalUrls? = null
)

data class AlbumMusic(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("release_date_precision")
	val releaseDatePrecision: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("uri")
	val uri: String? = null,

	@field:SerializedName("total_tracks")
	val totalTracks: Int? = null,

	@field:SerializedName("is_playable")
	val isPlayable: Boolean? = null,

	@field:SerializedName("artists")
	val artists: List<ArtistsItem?>? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("album_type")
	val albumType: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("external_urls")
	val externalUrls: ExternalUrls? = null
)

data class TracksMusic(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItemMusic?>? = null
)

data class ArtistsItemMusic(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("external_urls")
	val externalUrls: ExternalUrls? = null,

	@field:SerializedName("uri")
	val uri: String? = null
)

data class ExternalUrlsMusic(

	@field:SerializedName("spotify")
	val spotify: String? = null
)
