package com.ebookfrenzy.contactsproject.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebookfrenzy.contactsproject.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.contactsproject.Contact
import androidx.fragment.app.viewModels
import android.util.Log
import android.widget.Toast

import java.util.*

import com.ebookfrenzy.contactsproject.databinding.MainFragmentBinding


class MainFragment : Fragment(), OnDeleteClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var adapter: ContactListAdapter? = null
    val viewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.enterName.setText("")
        binding.enterNumber.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.enterName.text.toString()
            val number = binding.enterNumber.text.toString()
            if (name != "" && number != "") {
                val product = Contact(name, number)
                viewModel.insertContact(product)
                clearFields()
            } else {
                val message = "Incomplete information. Please enter a name and phone number."
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
        binding.findButton.setOnClickListener {
            val searchText = binding.enterName.text.toString()
            if (searchText != "") {
                viewModel.findContact(searchText)
            } else {
                val message = "Please enter search criteria in the 'Enter Name' field."
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
        binding.ascButton.setOnClickListener {
            viewModel.getAllContactAsc()
        }
        binding.descButton.setOnClickListener {
            viewModel.getAllContactDesc()
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                Log.i("KatieDebug", "MainFragment inside getAllContacts()?.observe")
                adapter?.setContactList(it)
            }
        })

        viewModel.getSearchResults().observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                Log.i("KatieDebug", "MainFragment inside getSearchResults()?.observe")
                adapter?.setContactList(it)
            }
        })

        viewModel.getNoResultsFound().observe(viewLifecycleOwner, { noResult ->
            noResult?.let {
                if(it) {
                    val message = "No contacts found. Please try entering different search criteria."
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.contact_card_layout, this)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

    override fun onDeleteIconClick(contactId: Int) {
        viewModel.deleteContact(contactId)
    }

}