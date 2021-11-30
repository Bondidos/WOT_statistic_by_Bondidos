package com.bondidos.wotstatisticbybondidos.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.AchievesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class AchievesFragment : Fragment() {

    companion object {
        fun newInstance() = AchievesFragment()
    }

    @Inject
    lateinit var viewModel: AchievesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.achieves_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated{
            viewModel.listOfAchieves.collect {

            }
        }
    }

}