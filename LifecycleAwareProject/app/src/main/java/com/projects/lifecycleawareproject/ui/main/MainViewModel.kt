package com.projects.lifecycleawareproject.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import java.time.LocalTime

class MainViewModel : ViewModel() {
    private var lifeCycleList = arrayListOf<String>()
    var lifecycleOutputText: MutableLiveData<String> = MutableLiveData()
    val requiresDividerList = arrayListOf("onResume", "onPause", "onDestroy")

    fun addLifecycleToOutputText(lifecycleText : String) {
        var stringToAdd = lifecycleText + " was fired on " + LocalTime.now()
        if (requiresDividerList.contains(lifecycleText)) {
            stringToAdd += "\n**********"
        }

        this.lifeCycleList.add(stringToAdd)
        this.lifecycleOutputText.value = convertArrayListToString()
    }

    private fun convertArrayListToString(): String {
        var stringList = ""

        if (this.lifeCycleList.size > 0) {
            for (lifecycle in this.lifeCycleList) {
                stringList += lifecycle + "\n"
            }
        }
        return stringList
    }
}