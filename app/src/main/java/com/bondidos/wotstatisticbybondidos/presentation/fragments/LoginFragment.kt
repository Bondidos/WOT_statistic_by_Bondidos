package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.LoginFragmentBinding
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
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
                findNavController().navigate(R.id.webViewFragment)
            }
        }
    }

    private fun setObservers(){

        viewModel.list.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                makeToast("Success")
                findNavController().navigate(R.id.searchPlayerFragment)
            }
            else makeToast("Fail")

        }
    }

    private fun makeToast(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}