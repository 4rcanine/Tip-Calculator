package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val billInput = findViewById<EditText>(R.id.bill_input)
        val radioGroup = findViewById<RadioGroup>(R.id.service_radio_group)
        val roundUpSwitch = findViewById<Switch>(R.id.round_up_switch)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val tipAmountText = findViewById<TextView>(R.id.tip_amount)



        calculateButton.setOnClickListener {
            val billAmount = billInput.text.toString().toDoubleOrNull()
            if (billAmount == null || billAmount == 0.0) {
                Toast.makeText(this, "Please enter a valid bill amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipPercentage = when (radioGroup.checkedRadioButtonId) {
                R.id.amazing_radio -> 0.20
                R.id.good_radio -> 0.18
                R.id.ok_radio -> 0.15
                else -> 0.0
            }
            var tip = billAmount * tipPercentage
            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            tipAmountText.text = String.format("Tip Amount: ₱%.2f", tip)
        }
    }
}
