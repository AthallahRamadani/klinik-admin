package com.example.klinikadmin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klinikadmin.R
import com.example.klinikadmin.models.PasienModel

class PasienAdapter(private val pasienList: ArrayList<PasienModel>) :
    RecyclerView.Adapter<PasienAdapter.ViewHolder>() {

    private lateinit var mListener: onItemCLickListener

    interface onItemCLickListener{
        fun onItemCLick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemCLickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pasien_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPasien = pasienList[position]
        holder.tvPasienName.text = currentPasien.pasienName
    }


    override fun getItemCount(): Int {
        return pasienList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemCLickListener) : RecyclerView.ViewHolder(itemView) {

        val tvPasienName : TextView = itemView.findViewById(R.id.tv_nama_pasien)

        init {
            itemView.setOnClickListener {
                clickListener.onItemCLick(adapterPosition)
            }
        }
    }

}