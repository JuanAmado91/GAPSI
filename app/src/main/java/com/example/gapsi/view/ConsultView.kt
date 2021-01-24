package com.example.gapsi.view

import com.example.gapsi.model.response.ResponseCatalog

interface ConsultView {

    fun result(result: ResponseCatalog)
    fun operationError()

}