package com.wepe.trydagger.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultsTv(

	@field:SerializedName("first_air_date")
	var firstAirDate: String? = null,

	@field:SerializedName("overview")
	var overview: String? = null,

	@field:SerializedName("original_language")
	var originalLanguage: String? = null,

	@field:SerializedName("genre_ids")
	var genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	var posterPath: String? = null,

	@field:SerializedName("origin_country")
	var originCountry: List<String?>? = null,

	@field:SerializedName("backdrop_path")
	var backdropPath: String? = null,

	@field:SerializedName("original_name")
	var originalName: String? = null,

	@field:SerializedName("popularity")
	var popularity: Double? = null,

	@field:SerializedName("vote_average")
	var voteAverage: Double? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("vote_count")
	var voteCount: Int? = null
) : Parcelable