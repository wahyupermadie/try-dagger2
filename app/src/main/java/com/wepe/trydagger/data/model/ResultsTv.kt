package com.wepe.trydagger.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_show")
@Parcelize
data class ResultsTv(

	@field:SerializedName("first_air_date")
	var firstAirDate: String,

	@field:SerializedName("overview")
	var overview: String,

	@field:SerializedName("original_language")
	var originalLanguage: String,

	@Ignore
	@field:SerializedName("genre_ids")
	var genreIds: List<Int>,

	@field:SerializedName("poster_path")
	var posterPath: String,

	@Ignore
	@field:SerializedName("origin_country")
	var originCountry: List<String>,

	@field:SerializedName("backdrop_path")
	var backdropPath: String,

	@field:SerializedName("original_name")
	var originalName: String,

	@field:SerializedName("popularity")
	var popularity: Double,

	@field:SerializedName("vote_average")
	var voteAverage: Double,

	@field:SerializedName("name")
	var name: String,

	@PrimaryKey
	@NonNull
	@field:SerializedName("id")
	var id: Int = 0,

	@field:SerializedName("vote_count")
	var voteCount: Int,

	var isFavorite : Boolean
) : Parcelable {
	constructor() : this("", "", "", arrayListOf(), "",
		arrayListOf(), "", "", 0.0,0.0,"",0,0,false)
}