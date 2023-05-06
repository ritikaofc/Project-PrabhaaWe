package com.example.x

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.x.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home_.*
import kotlinx.android.synthetic.main.fragment_home_.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home_Fragment : Fragment() {

    private var _binding:FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private val BASE_URL="https://3e38129a-da8d-4a9b-ad2e-f0e972e9dd38.mock.pstmn.io//"
    private var count:Int = 0
    private var temp:Int =0

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

        binding.btnExploreMore.setOnClickListener {
            var random=(4..9).random()
            Log.d("Random",random.toString())
            count += 1
            if(count!=1)
            {
                if(temp==random)
                {
                    random=(4..9).random()
                    count+=1
                }
            }
            temp=random
            val url=BASE_URL+random
            getData(url)
            Log.d("Random",url.toString())
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

    fun getData(url:String)
    {

        val progressDialog=ProgressDialog(context)
        progressDialog.setMessage("Please wait while data is being fetched")
        progressDialog.show()

        RetrofitInstance.apiInterface.getData(url).enqueue(object : Callback<responseDataClass?> {
            override fun onResponse(

                call: Call<responseDataClass?>,
                response: Response<responseDataClass?>
            ) {
                binding.launchYear.text=response.body()?.launchYear
                binding.vision.text=response.body()?.vision
                context?.let { Glide.with(it).load(response.body()?.img).into(binding.schemeImg) }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
                Toast.makeText(context,"${t.localizedMessage}",Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        })

    }
}
