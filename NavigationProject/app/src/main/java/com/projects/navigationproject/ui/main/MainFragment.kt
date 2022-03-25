package com.projects.navigationproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.navigationproject.R
import com.projects.navigationproject.databinding.MainFragmentBinding
import androidx.navigation.Navigation

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        binding.imageButton1.setOnClickListener {
            val action: MainFragmentDirections.MainToSecond =
                MainFragmentDirections.mainToSecond()
            action.setImageTitle("Image 1")
            action.setImageResourceID("android_image_1")
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageButton2.setOnClickListener {
            val action: MainFragmentDirections.MainToSecond =
                MainFragmentDirections.mainToSecond()
            action.setImageTitle("Image 2")
            action.setImageResourceID("android_image_2")
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageButton3.setOnClickListener {
            val action: MainFragmentDirections.MainToSecond =
                MainFragmentDirections.mainToSecond()
            action.setImageTitle("Image 3")
            action.setImageResourceID("android_image_3")
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}