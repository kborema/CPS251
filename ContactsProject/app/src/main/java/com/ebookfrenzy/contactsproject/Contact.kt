package com.ebookfrenzy.contactsproject

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var contactName: String? = null

    @ColumnInfo(name = "contactNumber")
    var contactNumber: String? = null

    constructor() {}

    constructor(id: Int, contactName: String, contactNumber: String) {
        this.contactName = contactName
        this.contactNumber = contactNumber
    }

    constructor(contactName: String, contactNumber: String) {
        this.contactName = contactName
        this.contactNumber = contactNumber
    }

}