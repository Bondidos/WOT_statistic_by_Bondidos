package com.bondidos.wotstatisticbybondidos.presentation.ui.login.dialog_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bondidos.wotstatisticbybondidos.databinding.FragmentCustomDialogBinding
import com.bondidos.wotstatisticbybondidos.presentation.ui.login.MainActivity

class ExitFragment : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnYes.setOnClickListener { (activity as MainActivity).finish() }
            btnNo.setOnClickListener { dismiss() }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}