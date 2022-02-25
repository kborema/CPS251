package com.projects.namesavedataproject1.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var nameText = ""
    private var namesList = arrayListOf<String>()

    fun addName(name: String) {
        this.nameText = name
        this.namesList.add(name)
    }

    fun getNamesList(): String {
        var listOfNames = ""

        for (name in namesList) {
            listOfNames += name + "\n"
        }

        return listOfNames
    }

    fun getNamesCount(): Int {
        return namesList.size
    }

}