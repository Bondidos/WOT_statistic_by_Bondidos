package com.bondidos.wotstatisticbybondidos.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel
    private lateinit var searchBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBtn = view.findViewById(R.id.search_player)

        searchBtn.setOnClickListener {
            viewModel.search("Legitim")
        }

        viewModel.list.observe(viewLifecycleOwner){
            if(it.isNotEmpty())
                Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
            else Toast.makeText(context,"Fail",Toast.LENGTH_LONG).show()
        }
    }
}