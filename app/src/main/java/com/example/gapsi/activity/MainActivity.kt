package com.example.gapsi.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gapsi.R
import com.example.gapsi.adapter.CatalogAdapter
import com.example.gapsi.adapter.PaginationScrollListener
import com.example.gapsi.fragments.TrendingFragment
import com.example.gapsi.model.response.ResponseMoviesPopular
import com.example.gapsi.model.response.Results
import com.example.gapsi.presenter.ConsultProductPresenter
import com.example.gapsi.presenter.ConsultProductPresenterImpl
import com.example.gapsi.view.ConsultView
import io.realm.Realm
import io.realm.RealmResults


class MainActivity : AppCompatActivity(), ConsultView {

    private var consultProductPresenter: ConsultProductPresenter? = null
    private lateinit var consult: EditText
    lateinit var openFragment: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var text1: TextView
    lateinit var mRecyclerView : RecyclerView
    private val mAdapter : CatalogAdapter = CatalogAdapter()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var page = 1
    var dataResult : ArrayList<Results> = ArrayList()
    private lateinit var  realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consultProductPresenter = ConsultProductPresenterImpl(this)
        consultProductPresenter!!.consult(page)
        initUI()
    }

    private fun initUI(){
        openFragment = findViewById(R.id.button)
        consult = findViewById(R.id.edtWordBrowser)
        progressBar = findViewById(R.id.progress_circular)
        text1 = findViewById(R.id.txtinit)

        mRecyclerView = findViewById(R.id.catalogRecycler)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


        openFragment.setOnClickListener {
            openFragment.visibility = View.INVISIBLE
            val fragment = TrendingFragment.newInstance()
            openFragment(fragment)
            true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        openFragment.visibility =View.VISIBLE
    }

    override fun result(result: ResponseMoviesPopular) {

        Log.e("tamaño", ""+ result.results.size)
        //saveMovies(result)
        //dataResult = result.results
      //  var data :RealmResults<ResponseMoviesPopular> = realm.where(ResponseMoviesPopular::class.java).findAll()
        progressBar.visibility = View.INVISIBLE
        text1.visibility = View.INVISIBLE
        mAdapter.catalogAdapter(result, this)
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

    private fun saveMovies(movies: ResponseMoviesPopular) {

    }

    override fun operationError() {

        Toast.makeText(this,"error", Toast.LENGTH_LONG).show()
        progressBar.visibility = View.INVISIBLE
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}