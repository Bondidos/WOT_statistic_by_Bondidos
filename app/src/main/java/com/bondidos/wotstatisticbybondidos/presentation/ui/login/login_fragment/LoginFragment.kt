package com.bondidos.wotstatisticbybondidos.presentation.ui.login.login_fragment

import android.content.Intent
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
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants
import com.bondidos.wotstatisticbybondidos.domain.other.Status.*
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.Statistic
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private var _binding: LoginFragmentBinding? = null
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

    private fun setListeners() {
        with(binding) {
            loginBtn.setOnClickListener {
                viewModel.logInWithWgOpenId()
            }
            continueBtn.setOnClickListener {
                viewModel.continueAsSavedUser()
            }
            logOut.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    private fun setObservers() {
        with(binding) {
            lifecycleScope.launchWhenCreated {
                viewModel.isDatabaseCreated.collect { resource ->
                    when (resource.status) {
                        LOADING -> {
                            loginProgressBar.isVisible = true
                        }
                        SUCCESS -> {
                            loginProgressBar.isVisible = false
                        }
                        ERROR -> {
                            loginProgressBar.isVisible = false
                            makeToast(requireContext(), resource.message ?: "")
                            continueBtn.isClickable = false
                            loginBtn.isClickable = false
                        }
                        else -> Unit
                    }
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.isExistSavedUser.collect { resource ->
                    when (resource.status) {
                        INITIALIZED -> {
                            continueBtn.isVisible = false
                        }
                        SUCCESS -> {
                            if (resource.data != null) {
                                loginBtn.isVisible = false
                                continueBtn.apply {
                                    isVisible = true
                                    text = resource.data.nickname
                                }
                            } else {
                                logOut.isVisible = false
                                continueBtn.isVisible = false
                                loginBtn.isVisible = true
                                makeToast(requireContext(), "Please Login")
                            }
                        }
                        ERROR -> {
                            makeToast(requireContext(), resource.message!!)
                            continueBtn.isVisible = false
                            binding.logOut.isVisible = false
                        }

                        else -> Unit
                    }
                }
            }

            // Navigation
            lifecycleScope.launchWhenCreated {
                viewModel.navigation.collect { event ->

                    when (event.getContentIfNotHandled()) {
                        Constants.NAVIGATE_TO_LOGIN -> findNavController().navigate(R.id.webViewFragment)
                        Constants.NAVIGATE_CONTINUE -> startActivity(
                            Intent(
                                requireContext(),
                                Statistic::class.java
                            )
                        )
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