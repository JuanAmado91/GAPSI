package com.example.gapsi.service.`interface`


import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.ResponseCatalog
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface WebService {

    @GET("search?")
    fun getCatalog(@Query("query") request: ProductRequest, @Header("X-IBM-Client-Id") token: String): Observable<ResponseCatalog>
}