package com.example.gapsi.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductRequest(
    @Expose
    @SerializedName("product")
    val product: String,
)