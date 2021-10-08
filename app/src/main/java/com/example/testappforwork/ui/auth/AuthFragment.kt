package com.example.testappforwork.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testappforwork.R
import com.example.testappforwork.databinding.FragmentAuthBinding
import com.example.testappforwork.models.*
import com.example.testappforwork.utilities.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

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
                && password.matches(passwordPattern) && password.isNotEmpty()
            ) {
                hideKeyboard()
                binding.illegalLoginOrPassword.visibility = View.GONE
                viewModel.auth(498817)
            } else {
                binding.illegalLoginOrPassword.visibility = View.VISIBLE
            }
        }

        viewModel.weatherLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is SuccessResult -> {
                    result.takeSuccess()?.let { response ->
                        showWeatherSnackbar(response)
                        showAuth()
                        binding.apply {
                            loginEditText.setText("")
                            passwordEditText.setText("")
                        }
                    }
                }
                is PendingResult -> {
                    showProgress()
                }
                is ErrorResult -> {
                    showAuth()
                    Snackbar.make(binding.root, R.string.error_request_auth, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun showProgress() {
        binding.apply {
            loginButton.visibility = View.INVISIBLE
            loginEditText.isEnabled = false
            passwordEditText.isEnabled = false
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showAuth() {
        binding.apply {
            loginButton.visibility = View.VISIBLE
            loginEditText.isEnabled = true
            passwordEditText.isEnabled = true
            progressBar.visibility = View.GONE
        }
    }

    private fun showWeatherSnackbar(response: WeatherResponse) {
        val weatherString =
            "Погода в ${response.cityName}е: ${response.main.temp} \u2103, " +
                    "${response.weather[0].description}.\n" +
                    "Влажность: ${response.main.humidity}%"
        Snackbar.make(binding.root, weatherString, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AuthFragment"
        const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}\$"
    }
}