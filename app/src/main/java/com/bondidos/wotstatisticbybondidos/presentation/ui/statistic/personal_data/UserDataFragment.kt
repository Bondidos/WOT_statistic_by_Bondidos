package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bondidos.wotstatisticbybondidos.databinding.UserDataFragmentBinding
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import com.bondidos.wotstatisticbybondidos.domain.other.Status.*
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter.DataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class UserDataFragment : Fragment() {

    @Inject
    lateinit var viewModel: UserDataViewModel
    private var _binding: UserDataFragmentBinding? = null
    private val binding: UserDataFragmentBinding get() = requireNotNull(_binding)
    private val userDataAdapter: DataAdapter by lazy { DataAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDataFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpRecycler(list: List<MultiViewModel>) = binding.userDataRecycler.apply {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        itemAnimator = (DefaultItemAnimator())
        userDataAdapter.setData(list)
        adapter = userDataAdapter
    }

    private fun setUpFragment() {
        lifecycleScope.launchWhenCreated {
            viewModel.listUserData.collect { resources ->
                when (resources.status) {
                    INITIALIZED -> Unit
                    LOADING -> binding.userDataProgressBar.isVisible = true
                    SUCCESS -> {
                        resources.data?.let {
                            setUpRecycler(it)
                        }
                        binding.userDataProgressBar.isVisible = false
                    }
                    ERROR -> {
                        binding.userDataProgressBar.isVisible = false
                        makeToast(requireContext(), resources.message ?: "Unknown error")
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