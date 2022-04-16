package com.ebookfrenzy.contactsproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import android.util.Log

class ContactRepository(application: Application) {

    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?
    val noResultsFound = MutableLiveData<Boolean>()

    init {
        val db: ContactRoomDatabase? =
            ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
    }

    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }
    private suspend fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }
    private suspend fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            var findResults = asyncFind(name).await()
            if (findResults.isNullOrEmpty()) {
                noResultsFound.value = true
            }
            else {
                searchResults.value = findResults!!
            }
        }
        noResultsFound.value = false
    }
    private suspend fun asyncFind(name: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContact(name)
    }

    fun getAscContacts() {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncGetAsc().await()
        }
    }
    private suspend fun asyncGetAsc(): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAscendingContacts()
    }

    fun getDescContacts() {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncGetDesc().await()
        }
    }
    private suspend fun asyncGetDesc(): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getDescendingContacts()
        }
}