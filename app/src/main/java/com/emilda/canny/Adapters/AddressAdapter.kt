package com.emilda.canny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.emilda.canny.R

class AddressAdapter :RecyclerView.Adapter<AddressAdapter.mViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.address_row,parent,false)
        return mViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

    }

    inner class mViewHolder(v:View):RecyclerView.ViewHolder(v){

    }
}