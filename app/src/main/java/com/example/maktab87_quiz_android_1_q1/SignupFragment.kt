package com.example.maktab87_quiz_android_1_q1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.maktab87_quiz_android_1_q1.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private val viewModel: LoginSignupViewModel by activityViewModels()
    private lateinit var binding: FragmentSignupBinding
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private var intentUserName = ""
    private var intentPassword = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        init()
        btnOnClickListener()
        return binding.root
    }

    private fun init() {
        etUserName = binding.inputUsername
        etPassword = binding.inputPassword
        intentUserName = viewModel.loginUserName.value.toString()
        intentPassword = viewModel.loginPassword.value.toString()
        etUserName.setText(intentUserName)
        etPassword.setText(intentPassword)
        viewModel.loginUserName.observe(viewLifecycleOwner) { username ->
            etUserName.setText(username)
        }
        viewModel.loginPassword.observe(viewLifecycleOwner) { password ->
            etPassword.setText(password)
        }

    }

    private fun passData() {
        val username = etUserName.text.toString()
        val password = etPassword.text.toString()
        if (username == "" || password == "") Toast.makeText(
            context,
            "Please type your information",
            Toast.LENGTH_SHORT
        ).show()
        else {
            viewModel.setSignUpUserName(etUserName.text.toString())
            viewModel.setSignUpPassword(etPassword.text.toString())
            binding.root.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    private fun btnOnClickListener() {
        binding.btnSignUpSecond.setOnClickListener {
            passData()
        }
    }
}