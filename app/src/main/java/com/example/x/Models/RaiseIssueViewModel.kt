package com.example.x.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.x.Repository.RaiseIssueRepository

class RaiseIssueViewModel:ViewModel() {
    private val repository : RaiseIssueRepository
    private val _allRaiseIssues=MutableLiveData<List<RaiseIssueDataClass>>()
    val allRaiseIssues:LiveData<List<RaiseIssueDataClass>> = _allRaiseIssues
    init{
        repository=RaiseIssueRepository().getInstance()
        repository.loadRaiseIssues(_allRaiseIssues)
    }
}