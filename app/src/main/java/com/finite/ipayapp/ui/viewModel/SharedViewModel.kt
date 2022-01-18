package com.finite.ipayapp.ui.viewModel

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database : DatabaseReference

class SharedViewModel : ViewModel() {

    // For passing params using navController
    val bigbazaar = "Big Bazaar"
    val vmart = "V-Mart"
    val easyday = "EasyDay"
    val max = "Max Fashion"
    val trends = "Reliance Trends"
    val globus = "Globus"
    val ss = "Shoppers Stop"
    val lifestyle = "LifeStyle"

    val currentshop = MutableLiveData<String>("test2")
    var currentshopcode = MutableLiveData<String>("defaultShopCode")
    var scancode = "defaultScanCode"
    var shopNameBill = "defaultBillName"

    var vmQty : MutableList<String> = mutableListOf()
    var vmName : MutableList<String> = mutableListOf()
    var vmPrice : MutableList<String> = mutableListOf() // IMP : DO ToString when getting data
    val vmTotal = MutableLiveData<Int>(0)
    var vmPaymentId = MutableLiveData<String>("default")


    fun addItemFromDB(code: String) {
        database = FirebaseDatabase.getInstance().getReference(currentshopcode.value!!)
        //database = FirebaseDatabase.getInstance().getReference(currentshopcode)
        database.child(scancode).get().addOnSuccessListener {
            if (it.exists()){

                val pname = it.child("pname").value.toString()
                val pprice = it.child("pprice").value.toString()

                vmQty.add("1 x ")
                vmName.add(pname)
                vmPrice.add(pprice)
                vmTotal.value = vmTotal.value?.plus(pprice.toInt())


                Log.d("ViewModelPassedDataTest","$pname = $pprice")

            }else{
                Log.d("ViewModelDoesNotExists", " currentshopecode = $currentshopcode ; scancode = $scancode")
            }

        }.addOnFailureListener{
            Log.d("ViewModelFailed","Failed")
        }
    }

}