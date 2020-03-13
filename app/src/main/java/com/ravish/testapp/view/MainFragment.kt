package com.ravish.testapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ravish.testapp.R
import com.ravish.testapp.commonUtils.CommonUtils
import com.ravish.testapp.model.Row
import com.ravish.testapp.view.adapter.CountryInfoRviewAdapter

class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var countryRview: RecyclerView? = null
    private var countryInfoRviewAdapter:CountryInfoRviewAdapter? = null
    private var swipeContainer:SwipeRefreshLayout? = null
    private var countryDetailsList:ArrayList<Row>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initView(view)
        initObserver()
        loadData()
        return view
    }

    private fun initView(v: View) {
        countryRview = v.findViewById(R.id.countryRview)
        countryRview?.layoutManager = LinearLayoutManager(context)
        countryDetailsList = ArrayList()

        // Create RecyclerView adapter to show data
        countryInfoRviewAdapter = CountryInfoRviewAdapter(countryDetailsList!!, context!!)
        countryRview?.adapter = countryInfoRviewAdapter
        swipeContainer = v.findViewById(R.id.swipeContainer) as SwipeRefreshLayout
        swipeContainer?.setOnRefreshListener(this)
        swipeContainer?.isRefreshing = true
    }

    private fun loadData() {
        val mainActivity = activity as MainActivity
        if(CommonUtils.isNetworkConnected(context!!)) {
            mainActivity.viewModel.downloadData()
        } else {
            Toast.makeText(activity?.applicationContext,  R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObserver() {
        (activity as MainActivity).viewModel.countryInfoLivedata.observe(this, Observer {
            swipeContainer?.isRefreshing = false
          if(it != null) {
              countryDetailsList?.clear()
              countryDetailsList?.addAll(it.rows!!)
              countryInfoRviewAdapter?.notifyDataSetChanged()
              (activity as MainActivity).supportActionBar?.title = it.title
          } else {
              Toast.makeText(activity?.applicationContext,  R.string.error_in_downloading_data, Toast.LENGTH_SHORT).show()
          }
        })
    }

    override fun onRefresh() {
        loadData()
    }
}
