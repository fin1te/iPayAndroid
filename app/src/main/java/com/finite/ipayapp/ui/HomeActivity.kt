package com.finite.ipayapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.finite.ipayapp.R
import com.finite.ipayapp.ui.viewModel.SharedViewModel
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener;

class HomeActivity : AppCompatActivity(), PaymentResultWithDataListener  {

    private val viewModel: SharedViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.statusMain)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        if (p1 != null) {
            viewModel.vmPaymentId.value = p1.paymentId
        }
        //Toast.makeText(this, "Success : $p0 ; ${viewModel.vmPaymentId.value}", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Failed : $p1", Toast.LENGTH_SHORT).show()
    }
}