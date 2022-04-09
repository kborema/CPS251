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

class ContactListAdapter(private val productItemLayout: Int) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contactName: TextView
        var contactNumber: TextView

        init {
            Log.i("ViewHolder", "inside ViewHolder init")
            contactName = itemView.findViewById(R.id.contactName)
            contactNumber = itemView.findViewById(R.id.contactNumber)
        }
    }

    private var contactList: List<Contact>? = null
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        holder.contactName.text = contactList?.get(listPosition)?.contactName
        holder.contactNumber.text = contactList?.get(listPosition)?.contactNumber
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
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var item: TextView = itemView.findViewById(R.id.product_row)
//    }
}