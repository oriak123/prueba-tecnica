package com.example.trabajo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*


class WebView : AppCompatActivity() {

    private val BASE_URL = "http://www.proximateapps.mobi/#aboutus"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

          // Configurar el webView

        // Crear clientes
         webview.webChromeClient = object :WebChromeClient(){

         }
        webview.webViewClient = object :WebViewClient(){

        }
        // Activar JavaScript accediendo a los settings, variables de configuracion de webview
        val settings = webview.settings
        settings.javaScriptEnabled = true

        webview.loadUrl(BASE_URL)
    }
}