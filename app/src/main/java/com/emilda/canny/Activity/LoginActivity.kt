package com.emilda.canny.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import com.emilda.canny.R
import kotlinx.android.synthetic.main.actvity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_login)
    }
    override fun onStart() {
        super.onStart()
        sent_otp_btn.setOnClickListener {
            val phone = phone_number.text.toString()
            Log.d("phone",phone)
            if(phone.length != 10){
                Toast.makeText(this,"Invalid Mobile Number", Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this,OtpActivity::class.java)
                intent.putExtra("phone",phone)
                startActivity(intent)
            }
        }

    }
}
