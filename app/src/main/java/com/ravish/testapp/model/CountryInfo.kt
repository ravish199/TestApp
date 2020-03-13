package com.ravish.testapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryInfo {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rows")
    @Expose
    var rows: ArrayList<Row>? = null

}