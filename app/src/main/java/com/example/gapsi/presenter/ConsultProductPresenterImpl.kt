package com.example.gapsi.presenter

import com.example.gapsi.interactor.CatalogInteractor
import com.example.gapsi.view.ConsultView

class ConsultProductPresenterImpl(view: ConsultView) : ConsultProductPresenter {

    private var view: ConsultView = view
    private var interactor: CatalogInteractor? = null
    override fun showProduct() {
        TODO("Not yet implemented")
    }

    override fun invalidOperation() {
        TODO("Not yet implemented")
    }
}