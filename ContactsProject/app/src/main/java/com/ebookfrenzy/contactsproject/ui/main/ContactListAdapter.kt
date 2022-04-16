package com.ebookfrenzy.contactsproject.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.ebookfrenzy.contactsproject.Contact
import com.ebookfrenzy.contactsproject.R

class ContactListAdapter(private val contactItemLayout: Int, private val listener: OnDeleteClickListener) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contactName: TextView
        var contactNumber: TextView
        var contactId: TextView
        var deleteIcon: ImageView
        var deleteIconClickListener: OnDeleteClickListener

        init {
            Log.i("ViewHolder", "inside ViewHolder init")
            contactName = itemView.findViewById(R.id.contactName)
            contactNumber = itemView.findViewById(R.id.contactNumber)
            contactId = itemView.findViewById(R.id.contactId)
            deleteIcon = itemView.findViewById(R.id.deleteIcon)
            deleteIconClickListener = listener
        }
    }

    private var contactList: List<Contact>? = null
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        Log.i("OnBindViewHolder", "inside")
        holder.contactName.text = contactList?.get(listPosition)?.contactName
        holder.contactNumber.text = contactList?.get(listPosition)?.contactNumber
        var contactId = contactList?.get(listPosition)?.id
        holder.contactId.text = contactId.toString()
        holder.deleteIcon.setOnClickListener{
                if (contactId != null) {
                    Log.i("OnBindViewHolder", "inside setOnClickListener contactIdNotNull")
                    listener.onDeleteIconClick(contactId)
                }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.contact_card_layout, parent, false)
        return ViewHolder(view)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }

}

interface OnDeleteClickListener {
    fun onDeleteIconClick(contactId: Int)
}