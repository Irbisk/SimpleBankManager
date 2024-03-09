package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.simplebankmanager.databinding.FragmentLoginBinding
import org.hyperskill.simplebankmanager.databinding.FragmentViewBalanceBinding

class ViewBalanceFragment : Fragment() {
    lateinit var binding: FragmentViewBalanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewBalanceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balance = arguments?.getDouble("balance")

        binding.viewBalanceAmountTextView.text = String.format("$%.2f", balance)

    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewBalanceFragment()
    }
}