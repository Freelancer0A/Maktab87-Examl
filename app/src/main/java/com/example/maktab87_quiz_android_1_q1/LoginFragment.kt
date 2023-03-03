package com.example.maktab87_quiz_android_1_q1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.maktab87_quiz_android_1_q1.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private val viewModel: LoginSignupViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var usernameEt: TextView
    private lateinit var passwordEt: TextView
    private lateinit var manager: PreferencesManager
    private var userName = ""
    private var password = ""
    private var intentUserName = ""
    private var intentPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        init()
        btlClickListeners()
        return binding.root
    }

    private fun init() {
        usernameEt = binding.inputUsername
        passwordEt = binding.inputPassword
        manager = PreferencesManager(requireContext())
        viewModel.loginUserName.observe(viewLifecycleOwner) { login ->
            usernameEt.text = login
        }
        viewModel.loginPassword.observe(viewLifecycleOwner) { password ->
            passwordEt.text = password
        }
        viewModel.signUpUserName.observe(viewLifecycleOwner) { login ->
            intentUserName = login
        }
        viewModel.signUpPassword.observe(viewLifecycleOwner) { password ->
            intentPassword = password
        }
    }

    private fun btlClickListeners() {
        binding.btnLogin.setOnClickListener {
            userName = usernameEt.text.toString()
            password = passwordEt.text.toString()
            if (userName == intentUserName && password == intentPassword && userName.trim() != "" && password.trim() != "") {
                manager.setFirstRun()
                val intent = Intent(requireContext(), TaskManagementActivity::class.java)
                startActivity(intent)
            } else if (userName == intentUserName && password != intentPassword && userName.trim() != "" && password.trim() != "") {
                Snackbar.make(binding.root, "password not match!", Snackbar.LENGTH_LONG).show()
            } else if (userName != intentUserName && password == intentPassword && userName.trim() != "" && password.trim() != "") {
                Snackbar.make(binding.root, "username not match!", Snackbar.LENGTH_LONG).show()
            } else if (userName.trim() == "" && password.trim() == "") {
                Toast.makeText(context, "userName & password is empty", Toast.LENGTH_SHORT)
                    .show()
            } else if (userName.trim() == "") {
                Toast.makeText(context, "userName is empty", Toast.LENGTH_SHORT).show()
            } else if (password.trim() == "" && userName.trim() != "") {
                Toast.makeText(context, "password is empty", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "Login information is not match with signUp information.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnSignup.setOnClickListener {
            viewModel.setLoginUserName(usernameEt.text.toString())
            viewModel.setLoginPassword(passwordEt.text.toString())
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }
}