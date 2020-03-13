package com.ravish.testapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravish.testapp.BR
import com.ravish.testapp.R
import com.ravish.testapp.databinding.CountryInfoRivewAdapterLayoutBinding
import com.ravish.testapp.model.Row

class CountryInfoRviewAdapter(private val rowlist: List<Row>, private val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val imageWidth = 120
    val imageHeight = 100
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.country_info_rivew_adapter_layout, parent, false)
                as CountryInfoRivewAdapterLayoutBinding
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).setData(position)
    }

    override fun getItemCount(): Int {
        return rowlist.size
    }

    private inner class CountryViewHolder(val binding: CountryInfoRivewAdapterLayoutBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
         fun setData(position: Int) {
             val row = rowlist[position]
             binding?.setVariable(BR.row, row)
             binding?.executePendingBindings()
             Glide.with(context).load(row.imageHref.toString()).placeholder(R.drawable.defaultimg)
                 .override(imageWidth, imageHeight)
                 .into(binding?.imageView!!)
        }
    }
}