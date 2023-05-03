package com.example.x

import android.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.x.databinding.FragmentHomeBinding
import de.hdodenhof.circleimageview.CircleImageView


class fragment_home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(com.example.x.R.layout.fragment_home, container, false)


    }

    companion object {

        @JvmStatic
        fun newInstance() = fragment_home()
    }
}