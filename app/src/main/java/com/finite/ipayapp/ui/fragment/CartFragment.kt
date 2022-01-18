package com.finite.ipayapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.finite.ipayapp.R
import com.finite.ipayapp.databinding.FragmentCartBinding
import com.finite.ipayapp.ui.viewModel.SharedViewModel


class CartFragment : Fragment() {

    val args: CartFragmentArgs by navArgs()
    private var binding : FragmentCartBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentCartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            sharedVm = viewModel
            cartFragment = this@CartFragment
        }

        binding?.shopTitle?.text = "Welcome to " + args.shopname
    }

}