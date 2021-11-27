package com.bondidos.wotstatisticbybondidos.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.wotstatisticbybondidos.databinding.SearchPlayerFragmentBinding
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.LoginViewModel
import javax.inject.Inject

class SearchPlayer : Fragment() {


    @Inject
    lateinit var viewModel: LoginViewModel

    private var _binding : SearchPlayerFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchPlayerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        setObservers()

    }

    private fun initRecycler() {
        binding.recycler.apply {

        }
    }

    private fun setObservers() {
//        viewModel.list.observe(viewLifecycleOwner){ list ->
//            //todo implement method
//        }
    }

}