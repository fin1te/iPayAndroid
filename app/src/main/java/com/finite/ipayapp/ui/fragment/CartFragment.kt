package com.finite.ipayapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.finite.ipayapp.R
import com.finite.ipayapp.adapter.CartAdapter
import com.finite.ipayapp.databinding.FragmentCartBinding
import com.finite.ipayapp.ui.viewModel.SharedViewModel
import com.razorpay.Checkout
import org.json.JSONObject


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

        Log.d("CartonViewCreated", "CartonViewCreated:")
        binding?.apply {
            sharedVm = viewModel
            cartFragment = this@CartFragment
            lifecycleOwner = viewLifecycleOwner
        }
        //val shopName = args.shopname

        //Checkout.preload(requireActivity().applicationContext)

        //binding?.shopTitle?.text = "Welcome to " + args.shopname

        binding?.fabAddedItem?.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToScannerFragment())
        }

        var qtyList = viewModel.vmQty
        var nameList = viewModel.vmName
        var priceList = viewModel.vmPrice

        binding?.cartRecView?.adapter = CartAdapter(qtyList,nameList,priceList)
        Log.d("CartLogAddedData", "qty : $qtyList;; name: $nameList;; price : $priceList;;")

        //binding?.cartRecView?.setHasFixedSize(true)

    }

    fun popBack() {
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToBaseFragment())
    }

    fun makePayment() {
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","iPay Checkout")
            options.put("description","Charges for Payment at \n ${viewModel.currentshopcode.value}")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://media.discordapp.net/attachments/821780395615846473/933170312063905882/icon_app.png")
            options.put("theme.color", "#471CE7");
            options.put("currency","INR");
            //options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", viewModel.vmTotal.value?.times(100)) //pass amount in currency subunits


            val prefill = JSONObject()
            prefill.put("email","test@example.com")
            prefill.put("contact","8369530805")

            options.put("prefill",prefill)
            co.open(requireActivity(),options)
        }catch (e: Exception){
            Toast.makeText(requireContext(),"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("CartonPausedCalled", "CartonPausedCalled:")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CartonResume", "CartonResume:")
        if(!viewModel.vmPaymentId.value.equals("default")) {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToBillFragment(paymentId = viewModel.vmPaymentId.value!!, shopName = viewModel.shopNameBill))
        }
    }

    fun openAboutFragment() {
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToAboutFragment())
    }

    fun openGuideFragment() {
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToGuideFragment())
    }

}