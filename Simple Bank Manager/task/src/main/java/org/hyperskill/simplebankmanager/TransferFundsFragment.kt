package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.databinding.FragmentTransferFundsBinding

class TransferFundsFragment : Fragment() {

    lateinit var binding: FragmentTransferFundsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransferFundsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.transferFundsButton.setOnClickListener {
            buttonAction()
        }

    }

    private fun buttonAction() {
        val bundle = arguments
        var error = false
        if (!binding.transferFundsAccountEditText.text.matches("(ca|sa)\\d{4}".toRegex())) {
            binding.transferFundsAccountEditText.error = "Invalid account number"
            error = true
        }
        val balance = bundle?.get("balance")
        val amount = binding.transferFundsAmountEditText.text.toString()
        if (amount.isEmpty() || amount.toDouble() <= 0) {
                binding.transferFundsAmountEditText.error = "Invalid amount"
            error = true
        }
        if (!error) {
            if (amount.toDouble() > balance.toString().toDouble()) {
                Toast.makeText(
                    context,
                    String.format("Not enough funds to transfer $%.2f", amount.toDouble()),
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    context,
                    String.format("Transferred $%.2f to account ${binding.transferFundsAccountEditText.text}", amount.toDouble()),
                    Toast.LENGTH_SHORT)
                    .show()
                bundle?.putDouble("balance", (balance.toString().toDouble() - amount.toDouble()))
                findNavController().navigate(R.id.action_transferFundsFragment_to_userMenuFragment, bundle)
            }

        }

    }


}