package com.ravish.testapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravish.formapp.api.RetrofitBuilder.Companion.getRetrofitObj
import com.ravish.testapp.model.CountryInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MyViewModel: ViewModel() {
     var countryInfoLivedata = MutableLiveData<CountryInfo>()

    // Downloading data
    fun downloadData() {
        getRetrofitObj()?.getData()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::onSuccess, this::onFailure)!!
    }


    // Send downloaded data to livedata observer
    private fun onSuccess(countryInfo: CountryInfo) {
        countryInfoLivedata.postValue(countryInfo)
    }

    // Send Error message to livedata observer
    private fun onFailure(error:Throwable) {
        Log.e(this::class.java.name, error.message!!)
        countryInfoLivedata.postValue(null)
    }

}