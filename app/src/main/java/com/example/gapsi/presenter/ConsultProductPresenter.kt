package com.example.gapsi.presenter

import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.ResponseCatalog

interface ConsultProductPresenter {

    fun consult(productRequest: ProductRequest)
    fun showResult(result: ResponseCatalog)
    fun invalidOperation()
}