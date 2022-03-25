package com.projects.navigationproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import com.projects.navigationproject.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            binding.ImageDescription.text = args.imageTitle
            var resourceID: Int = findResourceIDBasedOnResourceArgsPassed (args.imageResourceID)
            binding.image.setImageResource(resourceID)
        }
    }

    fun findResourceIDBasedOnResourceArgsPassed(imageName: String): Int {
        return when (imageName) {
            "android_image_1" -> R.drawable.android_image_1
            "android_image_2" -> R.drawable.android_image_2
            "android_image_3" -> R.drawable.android_image_3
            else -> R.drawable.android_image_1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}