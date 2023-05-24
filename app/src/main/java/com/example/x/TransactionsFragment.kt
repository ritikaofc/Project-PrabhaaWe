package com.example.x

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.x.databinding.FragmentTransactionsBinding



class TransactionsFragment : Fragment() {
    private var _binding:FragmentTransactionsBinding?= null
    private val binding get() = _binding!!
    private lateinit var adapter: TransactionsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionsList: ArrayList<Transactions>

    private lateinit var acc: Array<Int>
    private lateinit var sender:Array<String>
    private lateinit var amt:Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentTransactionsBinding.inflate(inflater,container,false)
        binding.backBtnTransactionHistory.setOnClickListener{
            val i = Intent(activity,MainActivity::class.java)
            startActivity(i)
        }
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = TransactionsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager= LinearLayoutManager(context)
        recyclerView=view.findViewById(R.id.transactionsRecyclerView)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        adapter= TransactionsAdapter(transactionsList)
        recyclerView.adapter=adapter

    }

    private fun dataInitialize(){
        transactionsList= arrayListOf<Transactions>()

        acc= arrayOf(
            1100000111,
            1000000022,
            1000000033,
            1000000044,
            1000000055,
            1000000066,
            1000000077,
            1000000088,

        )

        sender= arrayOf(
            getString(R.string.sender_1),
            getString(R.string.sender_2),
            getString(R.string.sender_3),
            getString(R.string.sender_4),
            getString(R.string.sender_5),
            getString(R.string.sender_6),
            getString(R.string.sender_7),
            getString(R.string.sender_8)

        )

        amt= arrayOf(
            "$1000",
            "$2000",
            "$3000",
            "$4000",
            "$5000",
            "$6000",
            "$7000",
            "$8000"

        )

        for(i in acc.indices){
            val transactions=Transactions(acc[i],sender[i],amt[i])
            transactionsList.add(transactions)
        }

    }
}