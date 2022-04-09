package com.projects.recycleviewprojectwithintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

import com.projects.recycleviewprojectwithintents.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras ?: return

        val cardPosition = extras.getInt("position")
        binding.titleTextView.text = Data.titles[Data.randomTitles[cardPosition]]
        binding.detailTextView.text = Data.details[Data.randomDetails[cardPosition]]
        binding.imageView.setImageResource(Data.images[Data.randomImages[cardPosition]])
    }
}