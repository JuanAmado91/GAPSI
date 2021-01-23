package com.example.gapsi.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseCatalog (
    @Expose
    @SerializedName("totalResults")
    var totalResults: Int,
    @Expose
    @SerializedName("page")
    var page: Int,
    @Expose
    @SerializedName("items")
    var items: List<DetailProduct>,
)