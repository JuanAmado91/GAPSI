package com.example.gapsi.service

import com.example.gapsi.service.`interface`.WebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/search?&query="play"

    var BASER_URL_CATALG = "https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/"


    private val clientToken = OkHttpClient
        .Builder()
        .addNetworkInterceptor(WSretrofit())
        .build()



    private val retrofitCatalog = Retrofit.Builder()
        .baseUrl(BASER_URL_CATALG)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientToken)
        .build()
        .create(WebService::class.java)




    fun buildService2(): WebService {
        return retrofitCatalog
    }

}