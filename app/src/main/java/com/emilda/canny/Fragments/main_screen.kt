package com.emilda.canny.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.emilda.canny.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_main_screen.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class main_screen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bottom Sheet
        bottom_sheet_dialog.setOnClickListener {
            val v = layoutInflater.inflate(R.layout.fragment_order_bottom_sheet,null)
            val dialog = BottomSheetDialog(context!!)
            dialog.setContentView(v)
            dialog.show()
        }
        //Bottom sheet Dialog Fragment
        bottom_sheet_diallog_fragment.setOnClickListener {
            val bottomSheet = OrderBottomSheet()
            bottomSheet.show(fragmentManager!!,bottomSheet.tag)
        }
    }


}
