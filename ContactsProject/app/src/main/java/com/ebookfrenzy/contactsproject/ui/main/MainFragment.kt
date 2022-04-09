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

import java.util.*

import com.ebookfrenzy.contactsproject.databinding.MainFragmentBinding


class MainFragment : Fragment() {

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
                //binding.productID.text = "Incomplete information"
                Log.i("MainFragment", "Incomplete Information, should be toast!!")
            }
        }
        binding.findButton.setOnClickListener {
            viewModel.findContact(binding.enterName.text.toString())
        }
        binding.ascButton.setOnClickListener {
            viewModel.getAllContactAsc()
        }
        binding.descButton.setOnClickListener {
            viewModel.getAllContactDesc()
        }
    }

    private fun observerSetup() {
        viewModel.getAllContacts()?.observe(this, Observer { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        })
    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.contact_card_layout)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

}