package com.projects.namesavedataproject2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.util.Log

class MainViewModel : ViewModel() {
    private var namesList = arrayListOf<String>()
    var nameValue: MutableLiveData<String> = MutableLiveData()
    var namesResult: MutableLiveData<String> = MutableLiveData()

    fun addNameToList() {
        nameValue.let {
            if (!it.value.equals("")) {
                this.namesList.add(it.value.toString())
                namesResult.value = convertArrayListToString()
            } else {
                namesResult.value = convertArrayListToString()
            }
        }
    }

    private fun convertArrayListToString(): String {
        var stringList = ""

        if (this.namesList.size > 0) {
            for (name in this.namesList) {
                stringList += name + "\n"
            }
        }

        return stringList
    }

}