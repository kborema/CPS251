package com.projects.namesavedataproject1.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.namesavedataproject1.R

import com.projects.namesavedataproject1.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (viewModel.getNamesCount() > 0) {
            binding.displayNames.text = viewModel.getNamesList()
        } else {
            binding.displayNames.text = "No names to display"
        }

        binding.addNameBtn.setOnClickListener {
            if (binding.editTextName.text.isNotEmpty()) {
                viewModel.addName(binding.editTextName.text.toString())
                binding.displayNames.text = viewModel.getNamesList()
            }
        }
    }

}