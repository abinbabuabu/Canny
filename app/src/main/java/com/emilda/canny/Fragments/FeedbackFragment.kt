package com.emilda.canny.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.emilda.canny.Activity.NavigationActivity

import com.emilda.canny.R

class FeedbackFragment : Fragment(),OnBackPressedCallback {

    override fun handleOnBackPressed(): Boolean {
        findNavController().navigate(R.id.main_screen)
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.addOnBackPressedCallback(viewLifecycleOwner,this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.removeOnBackPressedCallback(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.home ->{
                Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
                handleOnBackPressed()
                true
            }

            else ->super.onOptionsItemSelected(item)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.actionBar?.hide() ?: Log.d("action","No action bar")
    }
}





