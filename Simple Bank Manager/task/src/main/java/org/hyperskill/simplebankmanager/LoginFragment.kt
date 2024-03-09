package org.hyperskill.simplebankmanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.hyperskill.simplebankmanager.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = (view.context as AppCompatActivity).intent
        val usernameIntent = intent.extras?.getString("username") ?: "Lara"
        val passwordIntent = intent.extras?.getString("password") ?: "1234"
        val balance = intent.extras?.getDouble("balance") ?: 100.0

        binding.loginButton.setOnClickListener {
            val username = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            val loggedIn = (username == usernameIntent && passwordIntent == password)
            val loggingText = if (loggedIn) "logged in" else "invalid credentials"
            Toast.makeText(context, loggingText, Toast.LENGTH_SHORT).show()

            val bundle = Bundle()
            bundle.putString("username", username)
            bundle.putString("password", password)
            bundle.putDouble("balance", balance)

            if (loggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_userMenuFragment, bundle)

            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}