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
            binding.output.text = calculateTip(binding.billAmount.text.toString())
        }
    }

    private fun calculateTip(billAmt: String): String {
        return if(billAmt.isEmpty()) {
            "You must enter a bill amount!"
        } else {
            val billAmtToFloat = billAmt.toFloat()
            val tenPercentTotal = String.format("%.2f", billAmtToFloat * 1.10)
            val fifteenPercentTotal = String.format("%.2f", billAmtToFloat * 1.15)
            val twentyPercentTotal = String.format("%.2f", billAmtToFloat * 1.2)

            "The tips are as follows: \n\n" +
                    "10% = $tenPercentTotal \n" +
                    "15% = $fifteenPercentTotal \n" +
                    "20% = $twentyPercentTotal"
        }
    }




}