package com.example.testappforwork.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.testappforwork.R
import com.example.testappforwork.databinding.FragmentAuthBinding
import com.example.testappforwork.utilities.hideKeyboard

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val emailPattern = Regex(EMAIL_PATTERN)
    private val passwordPattern = Regex(PASSWORD_PATTERN)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.loginButton.setOnClickListener {
            val email = binding.loginEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            if (email.matches(emailPattern) && email.isNotEmpty()
                && password.matches(passwordPattern) && password.isNotEmpty()) {
                hideKeyboard()
                binding.illegalLoginOrPassword.visibility = View.GONE
            } else {
                binding.illegalLoginOrPassword.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}\$"
    }
}