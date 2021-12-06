package com.bondidos.wotstatisticbybondidos.presentation.ui.login.webview_fragment

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
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.LOGIN_URL
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.REDIRECT_URI
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

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
                    false -> {
                        makeToast(requireContext(),"Can't collect user data")
                        findNavController().navigate(R.id.loginFragment)
                    }
                    true -> {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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