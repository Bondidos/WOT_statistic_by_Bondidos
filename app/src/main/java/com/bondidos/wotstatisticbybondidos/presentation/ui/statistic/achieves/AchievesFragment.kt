package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.achieves

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bondidos.wotstatisticbybondidos.databinding.AchieveFragmentBinding
import com.bondidos.wotstatisticbybondidos.domain.other.Status
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter.DataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class AchievesFragment : Fragment() {

    @Inject
    lateinit var viewModel: AchievesViewModel
    private var _binding: AchieveFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userAchievesAdapter: DataAdapter by lazy { DataAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AchieveFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpRecycler() = binding.userDataRecycler.apply {
        layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        itemAnimator = (DefaultItemAnimator())
        adapter = userAchievesAdapter
    }

    private fun setUpFragment() {
        lifecycleScope.launchWhenCreated {
            viewModel.listUserAchieves.collect { resources ->

                when (resources.status) {
                    Status.INITIALIZED -> Unit
                    Status.LOADING -> binding.userDataProgressBar.isVisible = true
                    Status.SUCCESS -> {
                        try {
                            userAchievesAdapter.setData(resources.data ?: emptyList())
                        } catch (e: IllegalArgumentException) {
                            makeToast(requireContext(), e.toString())
                        }
                        setUpRecycler()
                        binding.userDataProgressBar.isVisible = false
                    }
                    Status.ERROR -> {
                        binding.userDataProgressBar.isVisible = false
                        makeToast(requireContext(), resources.message ?: "Unknown error")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}