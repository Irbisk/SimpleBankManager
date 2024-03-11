package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.databinding.FragmentLoginBinding
import org.hyperskill.simplebankmanager.databinding.FragmentUserMenuBinding

class UserMenuFragment : Fragment() {
    lateinit var binding: FragmentUserMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserMenuBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments
        val username = bundle?.getString("username")
        binding.userMenuWelcomeTextView.text = "Welcome $username"



        binding.userMenuViewBalanceButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_viewBalanceFragment, bundle)
        }

        binding.userMenuTransferFundsButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_transferFundsFragment, bundle)
        }

        binding.userMenuExchangeCalculatorButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_calculateExchangeFragment, bundle)
        }

        binding.userMenuPayBillsButton.setOnClickListener {
            findNavController().navigate(R.id.action_userMenuFragment_to_payBillsFragment, bundle)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = UserMenuFragment()

    }
}