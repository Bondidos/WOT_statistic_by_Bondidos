package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.LoginFragmentBinding
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.ACHIEVES_FRAGMENT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.WEB_VIEW_FRAGMENT
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import com.bondidos.wotstatisticbybondidos.domain.other.Status
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private var _binding : LoginFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
    }

    private fun setListeners(){
        with(binding) {
            loginBtn.setOnClickListener {
                viewModel.logIn()
            }
        }
    }

    private fun setObservers(){

        // update UI State
        lifecycleScope.launchWhenCreated {
            viewModel.isDatabaseCreated.collect{ resource ->
                when(resource.status){
                    Status.LOADING -> binding.loginProgressBar.isVisible = true
                    Status.SUCCESS -> {
                        resource.data?.let {
                            binding.loginProgressBar.isVisible = false
                            makeToast(requireContext(), "Achieves database initialized")
                        }
                    }
                    Status.ERROR -> {
                        binding.loginProgressBar.isVisible = false
                        makeToast(requireContext(), resource.message!!)
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.isExistSavedUser.collect{ resource ->
                when(resource.status){
                    Status.LOADING -> binding.loginBtn.text = "Searching user"
                    Status.SUCCESS -> resource.data?.let {
                        binding.loginBtn.text = "Continue as ${it.nickname}"
                    }
                    Status.ERROR -> {
                        makeToast(requireContext(), resource.message!!)
                        binding.loginBtn.text = "login with WG token"
                    }
                }
            }
        }

        // navigate WebView0
        lifecycleScope.launchWhenCreated {
            viewModel.navigation.collect{ event ->

                when(event.getContentIfNotHandled()) {
                    WEB_VIEW_FRAGMENT -> findNavController().navigate(R.id.webViewFragment)
                    ACHIEVES_FRAGMENT -> findNavController()   //todo
                }
            }
        }
    }
}