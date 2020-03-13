package com.ravish.formapp.api

import com.ravish.testapp.model.CountryInfo
import io.reactivex.Observable
import retrofit2.http.GET


 interface RestApis {

    @GET("facts")
    fun getData(): Observable<CountryInfo>

}