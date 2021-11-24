package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.bondidos.wotstatisticbybondidos.databinding.WebViewBinding
import dagger.hilt.android.AndroidEntryPoint

const val REDIRECT_URI = "https://developers.wargaming.net/reference/all/wot/auth/login/"
const val LOGIN_URL = "https://api.worldoftanks.ru/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F"

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private var _binding : WebViewBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WebViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeWebView()
        startLogin()
    }

    private fun startLogin() {
        binding.webView.loadUrl(LOGIN_URL)
    }

    private fun initializeWebView() {
        with(binding){
            webView.webViewClient = AuthWebViewClient()
            webView.settings.apply {
                true.also { javaScriptEnabled = it }
                cacheMode = WebSettings.LOAD_NO_CACHE
            }
        }
    }

    private fun saveUserInToPreferencesAndReturn(){

    }

    inner class AuthWebViewClient : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            // если URL содержит REDIRECT_URI, значит это нам пришел код
            // и WebView не надо открывать этот URL

            if (url?.contains(REDIRECT_URI, true)!!) {
                // парсим URL, чтобы извлечь из него код

                //Log.d("Log", url)
                    saveUserInToPreferencesAndReturn()
                return true
            }
            return false
        }
    }
}