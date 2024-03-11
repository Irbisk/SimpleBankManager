package org.hyperskill.simplebankmanager

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController


class PayBillsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_bills, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.userMenuFragment, arguments)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.payBillsCodeInputEditText)
        val button = view.findViewById<Button>(R.id.payBillsShowBillInfoButton)

        val defaultBillInfoMap = mapOf(
            "ELEC" to Triple("Electricity", "ELEC", 45.0),
            "GAS" to Triple("Gas", "GAS", 20.0),
            "WTR" to Triple("Water", "WTR", 25.5)
        )

        val billInfoMap = (arguments?.get("billInfo") ?: defaultBillInfoMap) as Map<String, Triple<String, String, Double>>

        button.setOnClickListener {
            if (!billInfoMap.containsKey(editText.text.toString())) {
                AlertDialog.Builder(context!!)
                    .setTitle("Error")
                    .setMessage("Wrong code")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                    }
                    .show()
            } else {
                var balance = arguments?.get("balance") as Double
                val triple = billInfoMap[editText.text.toString()]
                val message = String.format("Name: %s\nBillCode: %s\nAmount: $%.2f",
                    triple?.first, triple?.second, triple?.third)


                AlertDialog.Builder(context!!)
                    .setTitle("Bill info")
                    .setMessage(message)
                    .setPositiveButton("Confirm") { _, _ ->
                        if (balance >= triple?.third as Double) {
                            balance -= triple.third
                            arguments?.putDouble("balance", balance)
                            Toast.makeText(context, "Payment for bill ${triple.first}, was successful", Toast.LENGTH_SHORT).show()
                        } else {
                            AlertDialog.Builder(context!!)
                                .setTitle("Error")
                                .setMessage("Not enough funds")
                                .setPositiveButton(android.R.string.ok) { _, _ ->
                                }
                                .show()
                        }
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()

            }
        }



    }

}