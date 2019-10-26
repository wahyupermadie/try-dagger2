package com.wepe.trydagger.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class ResultsMovies(

	@field:SerializedName("overview")
	var overview: String? = "",

	@field:SerializedName("original_language")
	var originalLanguage: String? = "",

	@field:SerializedName("original_title")
	var originalTitle: String = "",

	@field:SerializedName("video")
	var video: Boolean = false,

	@field:SerializedName("title")
	var title: String? = "",

	@Ignore
	@field:SerializedName("genre_ids")
	var genreIds: List<Int> = arrayListOf(),

	@field:SerializedName("poster_path")
	var posterPath: String? = "",

	@field:SerializedName("backdrop_path")
	var backdropPath: String? = "",

	@field:SerializedName("release_date")
	var releaseDate: String? = "",

	@field:SerializedName("popularity")
	var popularity: Double? = 0.0,

	@field:SerializedName("vote_average")
	var voteAverage: Double? = 0.0,

	@PrimaryKey
	@NonNull
	@field:SerializedName("id")
	var id: Int = 0,

	@field:SerializedName("adult")
	var adult: Boolean? = false,

	@field:SerializedName("vote_count")
	var voteCount: Int? = 0,

	var isFavorite : Boolean? = false
) : Parcelable {
	constructor() : this("", "", "", false, "", arrayListOf(), "",
		"", "",0.0, 0.0, 0,false, 0, false)
}