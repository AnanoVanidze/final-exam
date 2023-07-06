package com.example.android_final.ui

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android_final.R
import com.example.android_final.databinding.FragmentSignInBinding
import com.example.android_final.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEt.text.toString()

            val isEmailValid =
                Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.text.toString()).matches()
            val isPasswordValid = binding.passwordEt.text.toString().length in 6..15

            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (isEmailValid && isPasswordValid) {
                    signIn(email, password)

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

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())

            } else {
                Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}