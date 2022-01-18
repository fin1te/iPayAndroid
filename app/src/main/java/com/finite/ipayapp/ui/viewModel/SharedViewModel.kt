package com.finite.ipayapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
    //var currentshop: MutableLiveData<String> = _currentshop

}