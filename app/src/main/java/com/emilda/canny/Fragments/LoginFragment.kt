package com.emilda.canny.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.emilda.canny.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onStart() {
        super.onStart()
        sent_otp_btn.setOnClickListener {
            val bundle = Bundle()
            val phone = phone_number.text.toString()
            Log.d("phone",phone)
            if(phone.length != 10){
                Toast.makeText(context,"Invalid Mobile Number",Toast.LENGTH_LONG).show()
            }
            else {
                bundle.putString("phone",phone)
                it.findNavController().navigate(R.id.action_loginFragment_to_OTP_Fragment,bundle)
            }
        }

    }


}
