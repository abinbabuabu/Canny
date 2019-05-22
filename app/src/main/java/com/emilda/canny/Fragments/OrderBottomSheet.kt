package com.emilda.canny.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilda.canny.Adapters.AddressAdapter

import com.emilda.canny.R
import kotlinx.android.synthetic.main.fragment_order_bottom_sheet.*


class OrderBottomSheet : RoundedBottomSheet(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        address_recycler.layoutManager = LinearLayoutManager(context)
        address_recycler.adapter = AddressAdapter()

    }


}
