package com.projects.lifecycleawareproject.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel : ViewModel() {
    private var lifeCycleList = arrayListOf<String>()
    var lifecycleOutputText: MutableLiveData<String> = MutableLiveData()

    fun addLifecycleToOutputText(lifecycleText : String) {
        Log.i("MainViewModel", "inside addLifecycleToOutputText: " + lifecycleText)
        Log.i("MainViewModel", "starting outputText value: " + this.lifecycleOutputText.toString())
        this.lifeCycleList.add(lifecycleText)
        this.lifecycleOutputText.value = convertArrayListToString()

        Log.i("MainViewModel", "ending outputText value: " + this.lifecycleOutputText.toString())
        Log.i("MainViewModel", "end of addLifecycleToOutputText")
    }

    private fun convertArrayListToString(): String {
        var stringList = ""

        if (this.lifeCycleList.size > 0) {
            for (lifecycle in this.lifeCycleList) {
                stringList += lifecycle + "\n"
            }
        }

        Log.i("MainViewModel", "inside convert:" + "\n" + stringList)
        return stringList
    }
}