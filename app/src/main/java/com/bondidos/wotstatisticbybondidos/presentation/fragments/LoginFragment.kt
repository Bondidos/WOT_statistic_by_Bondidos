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
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel.*
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel.LoginUiState.*
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
                viewModel.logInWithWgOpenId()
            }
            continueBtn.setOnClickListener {
                viewModel.continueAsSavedUser()
            }
        }
    }

    private fun setObservers() {
        with(binding) {
            // update UI State
            lifecycleScope.launchWhenCreated {
                // isDataBaseCreated
                /*viewModel.isDatabaseCreated.collect { uiState ->
                    when (uiState) {
                        is Loading -> {
                            loginProgressBar.isVisible = true
                        }
                        is Success -> {
                            loginProgressBar.isVisible = false
                            makeToast(requireContext(), "Achieves database initialized")
                        }
                        is Error -> {
                            loginProgressBar.isVisible = false
                            makeToast(requireContext(), uiState.message)
                            continueBtn.isClickable = false
                            loginBtn.isClickable = false
                        }
                        else -> Unit
                    }
                }*/

            }

            lifecycleScope.launchWhenCreated {
                /*viewModel.isExistSavedUser.collect { uiStatae ->
                    when (uiStatae) {
                        is Loading -> {
                            *//*loginBtn.text = "Searching user"*//*
                            continueBtn.isClickable = false
                        }
                        is Success -> uiStatae.data?.let {
                            *//*loginBtn.text = "Continue as ${it}"*//*
                            continueBtn.isClickable = true
                            continueBtn.text = it.nickname
                        }
                        is Error -> {
                            makeToast(requireContext(), uiStatae.message)
                            loginBtn.setText(R.string.login_with_wg_openid)
                            continueBtn.isClickable = false
                        }
                        else -> Unit
                    }
                }*/
            }

            // Navigation
            lifecycleScope.launchWhenCreated {
                viewModel.navigation.collect { event ->

                    when (event) {
                        NavigateEvent.ToWebView -> findNavController().navigate(R.id.webViewFragment)
                        NavigateEvent.ToUserAchieves -> findNavController().navigate(R.id.achievesFragment)
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}