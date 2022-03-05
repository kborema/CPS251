package com.projects.namesavedataproject2.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.projects.namesavedataproject2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    //LOGCAT TO TEST
    //private static final String TAG = "MyActivity"

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

        binding.addNameBtn.setOnClickListener {
            Log.i("MainFragment", "inside addNameBtn onClickListener")
            Log.i("MainFragment", binding.editTextName.text.toString())
            if (binding.editTextName.text.isNotEmpty()) {
                viewModel.addName(binding.editTextName.text.toString())
            }
        }

        val resultObserver = Observer<String> {
            result -> binding.displayNames.text = result
        }

        viewModel.getNamesList().observe(viewLifecycleOwner, resultObserver)
    }

//    private fun convertArrayListToString(arrayList: ArrayList<String>): String {
//        var stringList = ""
//
//        if (arrayList.size > 0) {
//            for (name in arrayList) {
//                stringList += name + "\n"
//            }
//        } else {
//            stringList = "No names to display"
//        }
//
//        return stringList
//    }

}