package com.example.gapsi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gapsi.R
import com.example.gapsi.adapter.CatalogAdapter
import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.model.response.ResponseCatalog
import com.example.gapsi.presenter.ConsultProductPresenter
import com.example.gapsi.presenter.ConsultProductPresenterImpl
import com.example.gapsi.view.ConsultView

class MainActivity : AppCompatActivity(), ConsultView {

    var consultProductPresenter: ConsultProductPresenter? = null
    private lateinit var consult: EditText
    lateinit var browser: Button
    lateinit var progressBar: ProgressBar
    private lateinit var text1: TextView
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : CatalogAdapter = CatalogAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consultProductPresenter = ConsultProductPresenterImpl(this)
        initUI()
    }

    private fun initUI(){
        browser = findViewById(R.id.button)
        consult = findViewById(R.id.edtWordBrowser)
        progressBar = findViewById(R.id.progress_circular)
        text1 = findViewById(R.id.txtinit)

        mRecyclerView = findViewById(R.id.catalogRecycler)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


        browser.setOnClickListener {
            if (browser.text.isNotEmpty()){
                progressBar.visibility = View.VISIBLE
                consultProductPresenter!!.consult(
                    ProductRequest(
                        consult.text.toString().trim()
                    )
                )
            }
            else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun result(result: ResponseCatalog) {

        Log.e("tamaño", ""+ result.items.size)
        progressBar.visibility = View.INVISIBLE
        text1.visibility = View.INVISIBLE
        mAdapter.catalogAdapter(result.items, this)
        mRecyclerView.adapter = mAdapter

    }

    override fun operationError() {

    }
}