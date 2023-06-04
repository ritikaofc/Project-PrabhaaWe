package com.example.x.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x.models.RaiseIssueDataClass
import com.example.x.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private  val raiseIssueList=ArrayList<RaiseIssueDataClass>()

    class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val issue: TextView = itemView.findViewById(R.id.issue)
        val fundAmt: TextView = itemView.findViewById(R.id.fund_amount)
        val tenure:TextView=itemView.findViewById(R.id.tenure_value)
        val supportDocument:TextView=itemView.findViewById(R.id.document_name)
        val descriptionIssue:TextView=itemView.findViewById(R.id.issue_description_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView=LayoutInflater.from(parent.context).inflate(R.layout.raise_issue_item_list,parent,false)
            return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
               val currentIssue=raiseIssueList[position]
                holder.issue.text=currentIssue.issueName
                holder.fundAmt.text=currentIssue.fundRequired
                holder.descriptionIssue.text=currentIssue.issueDescription
                holder.supportDocument.text=currentIssue.supportingDocument
                holder.tenure.text=currentIssue.tenure

    }
    fun updateRaiseIssueList(raiseIssueList:List<RaiseIssueDataClass>){
        this.raiseIssueList.clear()
        this.raiseIssueList.addAll(raiseIssueList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
      return raiseIssueList.size
    }


}