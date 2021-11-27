package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.LoginFragmentBinding
import com.bondidos.wotstatisticbybondidos.presentation.other.Resource
import com.bondidos.wotstatisticbybondidos.presentation.other.Status
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

            /*searchPlayer.setOnClickListener {
                if(searchEditText.text.isNotEmpty()){
                    viewModel.search(searchEditText.text.toString())
                }
                else makeToast("Please enter player's name")
            }*/

            logIn.setOnClickListener {
                viewModel.logIn()
                //findNavController().navigate(R.id.webViewFragment)
                 viewModel.search("LegitimateKiller")
            }
        }
    }

    private fun setObservers(){

        lifecycleScope.launchWhenCreated {
            viewModel.list.collect{ resource ->
                when(resource.status){
                    Status.LOADING -> binding.loginProgressBar.isVisible = true
                    Status.SUCCESS -> {
                        resource.data?.let { list ->
                            binding.loginProgressBar.isVisible = false
                            binding.textView2.text = list.toString()
                        }
                    }
                    Status.ERROR -> binding.textView2.text = "Connection problem"
                }


                }
        }
      /*
        viewModel.list
            .onEach { list ->
                list.isNotEmpty().let {
                    findNavController().navigate(R.id.searchPlayerFragment)
                }
            }
            .launchIn(lifecycleScope)*/

/*        viewModel.list.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                makeToast("Success")
                findNavController().navigate(R.id.searchPlayerFragment)
            }
            else makeToast("Fail")

        }*/
    }

    private fun makeToast(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}