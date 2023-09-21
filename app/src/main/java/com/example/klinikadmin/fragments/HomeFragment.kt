package com.example.klinikadmin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.klinikadmin.R


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setToolbarTitle("Home")
        return inflater.inflate(R.layout.fragment_home, container, false)

    }
    @Suppress("SameParameterValue")
    private fun setToolbarTitle(title: String) {
        val activity = activity as? AppCompatActivity
        activity?.supportActionBar?.title = title
    }

}