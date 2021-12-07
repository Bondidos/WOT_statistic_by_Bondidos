package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.personal_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bondidos.wotstatisticbybondidos.databinding.UserDataFragmentBinding
import com.bondidos.wotstatisticbybondidos.domain.other.Status.*
import com.bondidos.wotstatisticbybondidos.domain.other.makeToast
import com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter.UserDataAdapterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class UserDataFragment : Fragment() {

    private var _binding: UserDataFragmentBinding? = null
    private val binding: UserDataFragmentBinding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModel: UserDataViewModel

    private val userDataAdapter: UserDataAdapterAdapter by lazy { UserDataAdapterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDataFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

    }

    private fun setUpRecycler() = binding.userDataRecycler.apply {
        layoutManager = GridLayoutManager(requireContext(),2)
        adapter = userDataAdapter
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.listUserData.collect { resources ->

                when(resources.status){
                    INITIALIZED -> Unit
                    LOADING -> binding.userDataProgressBar.isVisible = true
                    SUCCESS -> {
                        try {
                            userDataAdapter.setData(resources.data ?: emptyList())
                        } catch (e: IllegalArgumentException){
                            makeToast(requireContext(),e.toString())
                        }
                        setUpRecycler()
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
}