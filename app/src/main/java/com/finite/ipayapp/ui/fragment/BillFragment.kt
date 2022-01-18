package com.finite.ipayapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.finite.ipayapp.R
import com.finite.ipayapp.adapter.CartAdapter
import com.finite.ipayapp.databinding.FragmentBillBinding
import com.finite.ipayapp.databinding.FragmentCartBinding
import com.finite.ipayapp.ui.viewModel.SharedViewModel


class BillFragment : Fragment() {

    val args: BillFragmentArgs by navArgs()
    private var binding : FragmentBillBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentBillBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            sharedVm = viewModel
            billFragment = this@BillFragment
            lifecycleOwner = viewLifecycleOwner
        }

        var qtyList = viewModel.vmQty
        var nameList = viewModel.vmName
        var priceList = viewModel.vmPrice

        binding?.billRecView?.adapter = CartAdapter(qtyList,nameList,priceList)

        binding?.fabCancelItem?.setOnClickListener {
            findNavController().navigate(BillFragmentDirections.actionBillFragmentToBaseFragment())
        }
    }
}