package com.example.gapsi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.gapsi.R
import com.example.gapsi.model.request.ProductRequest
import com.example.gapsi.presenter.ConsultProductPresenter
import com.example.gapsi.presenter.ConsultProductPresenterImpl
import com.example.gapsi.view.ConsultView

class MainActivity : AppCompatActivity(), ConsultView {

    var consultProductPresenter: ConsultProductPresenter? = null
    private lateinit var consult: EditText
    lateinit var browser: Button
    lateinit var progressBar: ProgressBar
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

    override fun result() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun operationError() {
        TODO("Not yet implemented")
    }
}