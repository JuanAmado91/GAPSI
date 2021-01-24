package com.example.gapsi.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailProduct (
    @Expose
    @SerializedName("id")
    var _id: String,
    @Expose
    @SerializedName("rating")
    var rating: String,
    @Expose
    @SerializedName("price")
    var price: String,
    @Expose
    @SerializedName("image")
    var image: String,
    @Expose
    @SerializedName("title")
    var title: String,
)