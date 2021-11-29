package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.WebViewBinding
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.WebViewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

const val REDIRECT_URI = "https://developers.wargaming.net/reference/all/wot/auth/login/"
const val LOGIN_URL = "https://api.worldoftanks.eu/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F"

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private var _binding : WebViewBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModel: WebViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WebViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        logIn()
    }

    private fun setObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.isSaved.collect{ event ->
                when(event.getContentIfNotHandled()){
                    "false" -> {
                        makeToast(requireContext(),"Can't collect user data")
                        findNavController().navigate(R.id.loginFragment)
                    }
                    "true" -> {
                        makeToast(requireContext(),"User saved")
                        findNavController().navigate(R.id.loginFragment)
                    }
                }
            }
        }
    }

    private fun logIn() {
        with(binding){
            webView.webViewClient = AuthWebViewClient()
            webView.settings.apply {
                true.also { javaScriptEnabled = it }
                cacheMode = WebSettings.LOAD_NO_CACHE
            }
            webView.loadUrl(LOGIN_URL)
        }
    }

    inner class AuthWebViewClient : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            if (url?.contains(REDIRECT_URI, true) == true) {
                //Log.d(javaClass.name, url)
                viewModel.saveUser(url)
                return true
            }
            return false
        }
    }
}