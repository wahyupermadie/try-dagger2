package com.wepe.trydagger.data.model

import com.google.gson.annotations.SerializedName

data class ResponseTv(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<ResultsTv?>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)