package com.example.android_final.ui

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.android_final.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signUpBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()
            val isPasswordValid = binding.passwordEt.text.toString().length in 6..15
            if (email.isNullOrEmpty() || password.isNullOrEmpty() || binding.repeatPasswordEt.text.toString().isNullOrEmpty()){
                Toast.makeText(
                    requireContext(),
                    "please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                if (isEmailValid && isPasswordValid) {
                    if (password == binding.repeatPasswordEt.text.toString()){
                        registerUser(
                            binding.emailEt.text.toString().trim(),
                            binding.passwordEt.text.toString().trim()
                        )
                    }else {
                        Toast.makeText(
                            requireContext(),
                            "passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "email or password is incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToMainFragment())
            } else {
                Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                    .show()
                Log.d("tag", task.exception.toString())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}