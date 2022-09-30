package com.example.classwork_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.classwork_9.adapters.ButtonAdapter
import com.example.classwork_9.adapters.IndicatorAdapter
import com.example.classwork_9.databinding.ActivityMainBinding
import com.example.classwork_9.model.Indicator
import com.example.classwork_9.model.ButtonTypes
import com.example.classwork_9.utils.ButtonUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val buttonAdapter = ButtonAdapter()
    private val indicatorAdapter = IndicatorAdapter()

    private var indicators = getEmptyIndicatorsList(size = 4)

    private var indicatorsPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initial()

        onClickListeners()

    }

    private fun initial() {
        binding.rvButtons.adapter = buttonAdapter
        buttonAdapter.submitList(ButtonUtil.BUTTONS)
        binding.rvIndicators.adapter = indicatorAdapter
        indicatorAdapter.submitList(indicators.toList())
    }

    private fun onClickListeners() {
        buttonAdapter.onNumericClickListener = {
            handleNumericButtonClick(it)
        }
        buttonAdapter.onRemoveClickListener = {
            handleRemoveButtonClick()
        }
    }

    private fun getEmptyIndicatorsList(size: Int) = MutableList(size) {
        Indicator(id = it, number = -1)
    }

    private fun handleNumericButtonClick(numeric: ButtonTypes.Numeric) {
        if (indicatorsPosition < indicators.size) {
            val indicator = indicators[indicatorsPosition]
            indicators[indicatorsPosition] = Indicator(id = indicator.id, number = numeric.number)
            indicatorsPosition += 1
            indicatorAdapter.submitList(indicators.toList())
        }
        if (indicatorsPosition == indicators.size) {
            if (ButtonUtil.CORRECT_PASSWORD == indicators) {
                Snackbar.make(binding.root, "Success!!!", Snackbar.LENGTH_LONG).show()
            } else {
                indicatorsPosition = 0
                indicators = getEmptyIndicatorsList(size = 4)
                indicatorAdapter.submitList(indicators.toList())
            }
        }
    }

    private fun handleRemoveButtonClick() {
        if (indicatorsPosition != 0) {
            indicatorsPosition -= 1
            val indicator = indicators[indicatorsPosition]
            indicators[indicatorsPosition] = Indicator(id = indicator.id, number = -1)
            d("myLog", indicators.toString())
            indicatorAdapter.submitList(indicators.toList())
        }
    }

}