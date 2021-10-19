package com.example.gapsi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gapsi.R
import com.example.gapsi.adapter.LanguagesAdapter
import com.example.gapsi.adapter.PaginationScrollListener
import com.example.gapsi.model.response.ResponseMoviesPopular
import com.example.gapsi.model.response.Results
import com.example.gapsi.presenter.ConsultProductPresenter
import com.example.gapsi.presenter.ConsultProductPresenterImpl
import com.example.gapsi.presenter.ConsultTrendingPresenterImpl
import com.example.gapsi.view.ConsultView


class TrendingFragment: Fragment(), ConsultView {

    var consultProductPresenter: ConsultProductPresenter? = null
    var page = 1
    lateinit var progressBar: ProgressBar
    private lateinit var text1: TextView
    lateinit var mRecyclerView : RecyclerView
    private val mAdapter : LanguagesAdapter = LanguagesAdapter()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var dataResult : ArrayList<Results> = ArrayList()

    companion object {
        fun newInstance(): TrendingFragment = TrendingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        android.util.Log.e("TrendingFragment", "llega a esta vista")
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultProductPresenter = ConsultTrendingPresenterImpl(this)
        consultProductPresenter!!.consult(page)

        progressBar = view.findViewById(R.id.progress_circular)
        text1 = view.findViewById(R.id.txtinit)

        mRecyclerView = view.findViewById(R.id.catalogRecycler)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun result(result: ResponseMoviesPopular) {
        dataResult = result.results
        progressBar.visibility = View.INVISIBLE
        text1.visibility = View.INVISIBLE
        mAdapter.catalogAdapter(result, context!!)
        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnScrollListener(object : PaginationScrollListener(mRecyclerView.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                page++

                getMoreItems()
            }
        })

    }
    fun getMoreItems() {
        progressBar.visibility = View.VISIBLE
        if (page > 1){
            consultProductPresenter!!.consult(page)
        }
        isLoading = false

        mAdapter.addData(dataResult)
    }

    override fun operationError() {

    }
}