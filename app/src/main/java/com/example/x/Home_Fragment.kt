package com.example.x

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.x.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home_.view.*
import retrofit2.Call
import retrofit2.Response


class Home_Fragment : Fragment() {

    private var _binding:FragmentHomeBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentHomeBinding.inflate(inflater,container,false)


        binding.userImage.setOnClickListener{
            val intent = Intent(context,ProfileActivity::class.java)
            startActivity(intent)
        }

//        OLD METHOD
//        val view= inflater.inflate(R.layout.fragment_home_, container, false)
//        val img=view.findViewById<ImageView>(R.id.userImage)
//        img.setOnClickListener{
//            val intent = Intent(context,ProfileActivity::class.java)
//            startActivity(intent)
//        }
//        return view

//        return the view that is the layout of this binding class
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = Home_Fragment()
    }

//    destroying the binding class which is initially is of null type
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
