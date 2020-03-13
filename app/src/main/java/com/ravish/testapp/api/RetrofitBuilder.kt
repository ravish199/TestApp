package com.ravish.formapp.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private const val baseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
        private var restApis: RestApis? =null
        fun getRetrofitObj(): RestApis? {
            return if(restApis == null) {
                val builder = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
                builder.create(RestApis::class.java)
            } else {
                restApis
            }
        }
    }
}