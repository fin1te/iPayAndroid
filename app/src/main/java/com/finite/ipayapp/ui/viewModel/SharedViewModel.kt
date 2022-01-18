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
    var currentshopcode = "defaultShopCode"
    var scancode = "defaultScanCode"





    fun addItemFromDB(code: String) {
        database = FirebaseDatabase.getInstance().getReference(currentshopcode)
        database.child(scancode).get().addOnSuccessListener {
            if (it.exists()){

                val pname = it.child("pname").value.toString()
                val pprice = it.child("pprice").value
                Log.d("PassedDataTest","$pname = $pprice")

            }else{
                //Toast.makeText(,"does not exists", Toast.LENGTH_SHORT).show()
                Log.d("DoesNotExists", " currentshopecode = $currentshopcode ; scancode = $scancode")
            }

        }.addOnFailureListener{
            //Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            Log.d("Failed","Failed")
        }
    }

}