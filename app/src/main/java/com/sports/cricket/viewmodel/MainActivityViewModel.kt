package com.sports.cricket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sports.cricket.service.model.CricketModel
import com.sports.cricket.service.repo.CricketRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivityViewModel";

class MainActivityViewModel(private val cricketRepo: CricketRepo = CricketRepo()) : ViewModel() {

    val cricketModel: MutableLiveData<CricketModel> = MutableLiveData()

    fun getMatchDetails() {
        viewModelScope.launch {
            try {
                val call = cricketRepo.getMatchDetails();
                if (call.isSuccessful) {
                    Log.e(TAG, "success" + call.body())
                    cricketModel.postValue(call.body());

                } else {
                    //failed


                }
            } catch (exception: Exception) {
            }
        }

    }


}