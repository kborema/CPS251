package com.projects.namesavedataproject2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel : ViewModel() {
    private var nameText = ""
    private var namesList = arrayListOf<String>()
    private var namesResult: MutableLiveData<String> = MutableLiveData()

    fun addName(name: String) {
        this.nameText = name
        this.namesList.add(name)
        namesResult.setValue(convertArrayListToString(this.namesList))
    }

    private fun convertArrayListToString(arrayList: ArrayList<String>): String {
        var stringList = ""

        if (arrayList.size > 0) {
            for (name in arrayList) {
                stringList += name + "\n"
            }
        } else {
            stringList = "No names to display"
        }

        return stringList
    }

    fun getNamesList(): MutableLiveData<String> {
        return namesResult
    }

}