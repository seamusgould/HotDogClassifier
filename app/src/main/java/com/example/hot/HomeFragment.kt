package com.example.hot

import com.example.hot.IPicture
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hot.databinding.ActivityMainBinding

class HomeFragment(var listener: IPicture.Listener) : Fragment() {
    var binding: ActivityMainBinding? = null
    fun onCreateView(inflater: LayoutInflater?): View {
        binding = ActivityMainBinding.inflate(inflater!!)
        return binding!!.root
    }
}