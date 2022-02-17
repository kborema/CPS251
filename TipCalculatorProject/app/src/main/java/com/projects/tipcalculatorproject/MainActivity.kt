package com.projects.tipcalculatorproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.projects.tipcalculatorproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipBtn.setOnClickListener{
            val billAmtEntered = binding.billAmount.text
            var outputString = ""

            if (billAmtEntered.isNotEmpty()) {
                outputString = calculateTip(billAmtEntered.toString().toFloat())
            } else {
                outputString = "You must enter a bill amount!"
            }
            binding.output.text = outputString
        }
    }

    private fun calculateTip(billAmt: Float): String {
        val tenPercentTotal = String.format("%.2f", billAmt * 1.10)
        val fifteenPercentTotal = String.format("%.2f", billAmt * 1.15)
        val twentyPercentTotal = String.format("%.2f", billAmt * 1.2)

        return "The tips are as follows: \n\n " +
                "10% = $tenPercentTotal \n" +
                "15% = $fifteenPercentTotal \n" +
                "20% = $twentyPercentTotal"


    }




}