package com.bondidos.wotstatisticbybondidos.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.presentation.viewModels.SearchPlayerViewModel

class SearchPlayer : Fragment() {

    companion object {
        fun newInstance() = SearchPlayer()
    }

    private lateinit var viewModel: SearchPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_player_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchPlayerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}