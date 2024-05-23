package com.example.tiptime
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import kotlin.math.ceil
class MainActivity : AppCompatActivity() {
    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptions: RadioGroup
    private lateinit var calculateButton: Button
    private lateinit var tipResult: TextView
    private lateinit var roundUpSwitch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        costOfServiceEditText = findViewById(R.id.cost_of_service_edit_text)
        tipOptions = findViewById(R.id.tip_options)
        calculateButton = findViewById(R.id.calculate_button)
        tipResult = findViewById(R.id.tip_result)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton.setOnClickListener {
            calculateTip()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun calculateTip() {
        val costOfService = costOfServiceEditText.text.toString().toDoubleOrNull()
        if (costOfService == null) {
            tipResult.text = getString(R.string.invalid_input)
            return
        }
        val selectedTipPercentage = when (tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.15
        }
        var tipAmount = costOfService * selectedTipPercentage
        if (roundUpSwitch.isChecked) {
            tipAmount = ceil(tipAmount)
        }
        val formattedTipAmount = String.format("%.2f", tipAmount)
        tipResult.text = getString(R.string.tip_amount_result, formattedTipAmount)
    }
}
