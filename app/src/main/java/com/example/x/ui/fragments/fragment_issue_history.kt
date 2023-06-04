package com.example.x.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.x.ui.adapter.MyAdapter
import com.example.x.models.RaiseIssueViewModel
import com.example.x.R
import com.example.x.databinding.FragmentIssueHistoryBinding
import com.example.x.ui.activities.MainActivity


class fragment_issue_history : Fragment() {
    private var _binding: FragmentIssueHistoryBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: RaiseIssueViewModel
    private lateinit var raiseIssueRecyclerView: RecyclerView
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentIssueHistoryBinding.inflate(inflater,container,false)
        binding.backBtnIssueHistory.setOnClickListener{
            val i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = fragment_issue_history()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        raiseIssueRecyclerView=view.findViewById(R.id.raiseIssueRecyclerView)
        raiseIssueRecyclerView.layoutManager= LinearLayoutManager(context)
        raiseIssueRecyclerView.setHasFixedSize(true)
        adapter= MyAdapter()
        raiseIssueRecyclerView.adapter=adapter
        viewModel= ViewModelProvider(this).get(RaiseIssueViewModel::class.java)
        viewModel.allRaiseIssues.observe(viewLifecycleOwner, Observer{
            adapter.updateRaiseIssueList(it)
        })

    }
}