package com.emilda.canny.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.emilda.canny.Activity.MainActivity
import com.emilda.canny.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_otp.*
import java.util.concurrent.TimeUnit


class OTP_Fragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    var number: String? = null
    var storedVerificationId:String? = null
    var resendToken: PhoneAuthProvider.ForceResendingToken? =null

    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("Success", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w("Error", "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String?,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d("Message sent", "onCodeSent:" + verificationId!!)
            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_OTP_Fragment_to_loginFragment)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(baseContext)
        mAuth = FirebaseAuth.getInstance()


    }


    override fun onStart() {
        super.onStart()
        number = arguments?.getString("phone")
        number = "+91$number"

        if (number != null)
            Log.d("Number", number)
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number!!,      // Phone number to verify
                60,               // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                ,             // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks
        verify_otp.setOnClickListener {
            var code = otp_code.text.toString()
            Log.d("Code",code)
            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
            signInWithPhoneAuthCredential(credential)
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(context,"Otp Verified Sucessfully",Toast.LENGTH_LONG).show()
                    Log.d("success", "signInWithCredential:success")

                    val user = task.result?.user
                    Log.d("User",user?.phoneNumber)
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("Error", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }








}
