package com.emilda.canny.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emilda.canny.R
import kotlinx.android.synthetic.main.fragment_feedback.*

class FeedbackFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar:Toolbar= activity!!.findViewById(R.id.toolbar)
        toolbar.visibility = View.GONE

        back_button_feedback.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.home ->{
                Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
                true
            }

            else ->super.onOptionsItemSelected(item)
        }

    }

}





