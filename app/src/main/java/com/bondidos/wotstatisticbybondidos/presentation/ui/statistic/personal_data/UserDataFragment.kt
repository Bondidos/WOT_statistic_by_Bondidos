package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bondidos.wotstatisticbybondidos.databinding.UserDataFragmentBinding
import com.bondidos.wotstatisticbybondidos.presentation.recycler_adapter.AchievesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDataFragment : Fragment() {

    private var _binding: UserDataFragmentBinding? = null
    private val binding: UserDataFragmentBinding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModel: UserDataViewModel

    private val achievesAdapter: AchievesAdapter = AchievesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDataFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*setUpRecycler()
        setUpObservers()*/
    }

    private fun setUpRecycler() = binding.achievesRecycler.apply {
        adapter = achievesAdapter
        layoutManager = GridLayoutManager(requireContext(),5)
    }

   /* private fun setUpObservers() {
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
    }*/

}