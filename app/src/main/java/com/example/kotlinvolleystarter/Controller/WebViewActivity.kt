package com.example.kotlinvolleystarter.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinvolleystarter.R
import com.example.kotlinvolleystarter.Utils.EXTRA_LINK
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var extras = intent.extras

        if (extras != null) {
            var link = extras.get(EXTRA_LINK)

            webView.loadUrl(link.toString())


        }

    }
}
