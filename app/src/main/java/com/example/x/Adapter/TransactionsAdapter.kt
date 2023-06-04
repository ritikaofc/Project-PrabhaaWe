package com.example.x.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x.R
import com.example.x.Models.Transactions

class TransactionsAdapter(private val transactionsList : ArrayList<Transactions> ) :RecyclerView.Adapter<TransactionsAdapter.TViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return TViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        val currentItem=transactionsList[position]
        holder.tvAccNo.text= currentItem.accNo.toString()
        holder.tvSenderName.text=currentItem.senderName
        holder.tvAmt.text=currentItem.amount.toString()
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    class TViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvAccNo : TextView=itemView.findViewById(R.id.account_no)
        val tvSenderName : TextView=itemView.findViewById(R.id.sender_name)
        val tvAmt: TextView=itemView.findViewById(R.id.amt)
    }
}