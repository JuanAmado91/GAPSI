package com.example.gapsi.interactor

import com.example.gapsi.model.request.ProductRequest

interface CatalogInteractor {

    fun getCatalogInteractor(productRequest: ProductRequest, token: String)
}