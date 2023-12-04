package com.example.cleansound.Model

import com.google.gson.annotations.SerializedName

data class ResponseUserSpotify(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("images")
	val images: List<ImagesItemUser?>? = null,

	@field:SerializedName("product")
	val product: String? = null,

	@field:SerializedName("followers")
	val followers: Followers? = null,

	@field:SerializedName("explicit_content")
	val explicitContent: ExplicitContent? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("external_urls")
	val externalUrls: ExternalUrlsUser? = null,

	@field:SerializedName("uri")
	val uri: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class ImagesItemUser(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Followers(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("href")
	val href: String? = null
)

data class ExplicitContent(

	@field:SerializedName("filter_locked")
	val filterLocked: Boolean? = null,

	@field:SerializedName("filter_enabled")
	val filterEnabled: Boolean? = null
)

data class ExternalUrlsUser(

	@field:SerializedName("spotify")
	val spotify: String? = null
)
