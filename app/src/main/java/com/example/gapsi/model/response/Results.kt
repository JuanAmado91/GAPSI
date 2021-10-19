package com.example.gapsi.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Results (
    @Expose
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @Expose
    @SerializedName("genre_ids")
    var genre_ids: ArrayList<String>,
    @Expose
    @SerializedName("original_language")
    var original_language: String,
    @Expose
    @SerializedName("original_title")
    var original_title: String,
    @Expose
    @SerializedName("title")
    var title: String,
    @Expose
    @SerializedName("original_name")
    var original_name: String,
    @Expose
    @SerializedName("poster_path")
    var poster_path: String,
    @Expose
    @SerializedName("vote_count")
    var vote_count: String,
    @Expose
    @SerializedName("vote_average")
    var vote_average: String,
    @Expose
    @SerializedName("origin_country")
    var origin_country: ArrayList<String>,
    @Expose
    @SerializedName("overview")
    var overview: String,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("popularity")
    var popularity: Double,
    @Expose
    @SerializedName("media_type")
    var media_type: String

)