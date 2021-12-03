package com.bondidos.wotstatisticbybondidos.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.GridLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.AchievesFragmentBinding
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.recycler_adapter.AchievesAdapter
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel.AchievesUiState
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel.AchievesUiState.Error
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel.AchievesUiState.Loading
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel.AchievesUiState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class AchievesFragment : Fragment() {

    private var _binding: AchievesFragmentBinding? = null
    private val binding: AchievesFragmentBinding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModel: AchievesViewModel

    private val achievesAdapter: AchievesAdapter = AchievesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AchievesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpObservers()
    }

    private fun setUpRecycler() = binding.achievesRecycler.apply {
        adapter = achievesAdapter
        layoutManager = GridLayoutManager(requireContext(),5)
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.listOfAchieves.collect { uiState ->

                when(uiState){
                    is Loading -> binding.achieveProgressBar.isVisible = true
                    is Success -> {
                        achievesAdapter.submitList(uiState.data)
                        binding.achieveProgressBar.isVisible = false
                    }
                    is Error -> {
                        makeToast(requireContext(), uiState.message)
                        binding.achieveProgressBar.isVisible = false
                    }
                    else -> Unit
                }
            }
        }
    }

}