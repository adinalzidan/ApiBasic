package com.example.apibasic.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apibasic.network.GithubApi
import com.example.apibasic.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(username: String) : ViewModel() {
    private val _item = MutableLiveData<GithubData>()

    val item: LiveData<GithubData>
        get() = _item

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)


    init {
        crScope.launch {

            try {
                val result = GithubApi.retrofitService.showUser(username)

                    _item.value = result
            } catch (t: Throwable) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}