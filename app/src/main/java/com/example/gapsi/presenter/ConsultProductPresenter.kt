package com.example.gapsi.presenter

import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.Languages
import com.example.gapsi.model.response.ResponseCatalog
import com.example.gapsi.model.response.ResponseMoviesPopular

interface ConsultProductPresenter {

    fun consult(page: Int)
    fun showResult(result: ResponseMoviesPopular)
    fun invalidOperation()
}