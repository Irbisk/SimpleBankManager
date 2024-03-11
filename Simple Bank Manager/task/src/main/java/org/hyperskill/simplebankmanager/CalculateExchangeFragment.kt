package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import org.hyperskill.simplebankmanager.databinding.FragmentCalculateExchangeBinding

class CalculateExchangeFragment : Fragment() {
    lateinit var binding: FragmentCalculateExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculateExchangeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val defaultMap = mapOf(
            "EUR" to mapOf(
                "GBP" to 0.5,
                "USD" to 2.0
            ),
            "GBP" to mapOf(
                "EUR" to 2.0,
                "USD" to 4.0
            ),
            "USD" to mapOf(
                "EUR" to 0.5,
                "GBP" to 0.25
            )
        )

        val exchangeRate = bundle?.get("exchangeMap") ?: defaultMap

        binding.calculateExchangeFromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == binding.calculateExchangeToSpinner.selectedItemPosition) {
                    Toast.makeText(context, "Cannot convert to same currency", Toast.LENGTH_SHORT).show()
                    binding.calculateExchangeToSpinner.setSelection(getSpinnerToSelection())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.calculateExchangeToSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == binding.calculateExchangeFromSpinner.selectedItemPosition) {
                    Toast.makeText(context, "Cannot convert to same currency", Toast.LENGTH_SHORT).show()
                    p0?.setSelection(getSpinnerToSelection())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.calculateExchangeButton.setOnClickListener {
            val rateFrom = binding.calculateExchangeFromSpinner.selectedItem.toString()
            val rateTo = binding.calculateExchangeToSpinner.selectedItem.toString()
            if (binding.calculateExchangeAmountEditText.text.isEmpty()) {
                Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show()
                binding.calculateExchangeAmountEditText.error = "Enter amount"
            } else {
                val exchangeRateMap = exchangeRate as Map<String, Map<String, Double>>
                binding.calculateExchangeDisplayTextView.text = calculate(exchangeRateMap[rateFrom]?.getValue(rateTo)!!)
            }
        }

    }

    fun calculate(rate: Double): String {
        val amountFrom = binding.calculateExchangeAmountEditText.text.toString().toDouble()
        val result = amountFrom * rate
        val symbolFrom = getSymbol(binding.calculateExchangeFromSpinner.selectedItemPosition)
        val symbolTo = getSymbol(binding.calculateExchangeToSpinner.selectedItemPosition)
        return String.format("$symbolFrom%.2f = $symbolTo%.2f", amountFrom, result)
    }

    fun getSymbol(currencyIndex: Int): String {
        return when (currencyIndex) {
            0 -> "€"
            1 -> "£"
            else -> "$"
        }
    }

    fun getSpinnerToSelection(): Int {
        var selection = binding.calculateExchangeToSpinner.selectedItemPosition
        selection++
        if (selection == 3) selection = 0
        return selection
    }

    companion object {

        @JvmStatic
        fun newInstance() = CalculateExchangeFragment()

    }
}