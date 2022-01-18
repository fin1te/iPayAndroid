package com.finite.ipayapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.finite.ipayapp.databinding.FragmentBaseBinding
import com.finite.ipayapp.ui.viewModel.SharedViewModel


class BaseFragment : Fragment() {

    private var binding : FragmentBaseBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            sharedVm = viewModel
            baseFragment = this@BaseFragment
        }
    }

    fun openCartFragment(shopname : String) {
        viewModel.currentshopcode = shopname
        viewModel.currentshop.value = "Welcome To $shopname"
        val action = BaseFragmentDirections.actionBaseFragmentToCartFragment(shopname)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.vmQty.clear()
        viewModel.vmName.clear()
        viewModel.vmPrice.clear()

        viewModel.currentshopcode = "defaultShopCode"
        viewModel.scancode = "defaultScanCode"
        viewModel.currentshop.value = "test2"
        viewModel.vmTotal.value = 0
    }
}