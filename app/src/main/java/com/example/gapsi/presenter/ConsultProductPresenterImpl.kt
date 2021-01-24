package com.example.gapsi.presenter

import com.example.gapsi.interactor.CatalogInteractor
import com.example.gapsi.interactor.CatalogInteractorImpl
import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.ResponseCatalog
import com.example.gapsi.view.ConsultView

class ConsultProductPresenterImpl(view: ConsultView?) : ConsultProductPresenter {

    private var view: ConsultView? = view
    private var interactor: CatalogInteractor? = null


    override fun consult(productRequest: ProductRequest) {
        interactor = CatalogInteractorImpl(this)
        if (view != null){
            interactor!!.getCatalogInteractor(productRequest.product.toString(), "adb8204d-d574-4394-8c1a-53226a40876e")
        }
    }

    override fun showResult(result: ResponseCatalog) {
        if (view != null){
            view!!.result(result)
        }
    }


    override fun invalidOperation() {
        if (view != null){
            view!!.operationError()
        }
    }
}