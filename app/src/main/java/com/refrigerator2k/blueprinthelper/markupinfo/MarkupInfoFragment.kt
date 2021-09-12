package com.refrigerator2k.blueprinthelper.markupinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.databinding.FragmentMarkupInfoBinding

class MarkupInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMarkupInfoBinding.inflate(inflater, container, false)
        val args = MarkupInfoFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = MarkupInfoViewModelFactory(args.text, args.areaWidth, args.markupWidth)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MarkupInfoViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.markupInfoViewModel = viewModel

        return binding.root
    }
}
