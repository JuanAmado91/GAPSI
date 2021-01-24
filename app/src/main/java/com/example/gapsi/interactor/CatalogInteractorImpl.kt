package com.example.gapsi.interactor

import android.util.Log
import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.ResponseCatalog
import com.example.gapsi.presenter.ConsultProductPresenter
import com.example.gapsi.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatalogInteractorImpl(consultProductPresenterImpl: ConsultProductPresenter) : CatalogInteractor {

    private val presenter: ConsultProductPresenter? = consultProductPresenterImpl

    private fun onResponse(response: ResponseCatalog) {
       // Log.e("succes", response.raw().request().url())
        Log.e("succes", response.totalResults.toString())
        Log.e("succes", response.items.size.toString())
        presenter!!.showResult(response)
    }

    private fun onFailure(response: Any) {
        Log.e("Error", response.toString())
        presenter!!.invalidOperation()
    }

    override fun getCatalogInteractor(productRequest: String, token: String) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitClient.buildService2()
                .getCatalog(productRequest,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }
}