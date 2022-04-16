package com.ebookfrenzy.contactsproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebookfrenzy.contactsproject.Contact
import com.ebookfrenzy.contactsproject.ContactRepository
import android.util.Log

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository = ContactRepository(application)
    private var allContacts: LiveData<List<Contact>>?
    private val searchResults: MutableLiveData<List<Contact>>
    private val noResultsFound: MutableLiveData<Boolean>

    init {
        allContacts = repository.allContacts
        searchResults = repository.searchResults
        noResultsFound = repository.noResultsFound
    }

    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }
    fun findContact(name: String){
        return repository.findContact(name)
    }
    fun deleteContact(id: Int) {
        repository.deleteContact(id)
    }
    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults
    }

    fun getAllContacts(): LiveData<List<Contact>>? {
        return allContacts
    }

    fun getNoResultsFound(): MutableLiveData<Boolean> {
        return noResultsFound
    }

    fun getAllContactAsc(){
        repository.getAscContacts()
    }

    fun getAllContactDesc() {
        repository.getDescContacts()

    }
}