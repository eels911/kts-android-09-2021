package com.example.android.unsplashmobile

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.unsplashmobile.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: StateLoginVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginValidData.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                binding.btnLogin.isEnabled = loginFormState
            }
        )

        setButtonClickListener()
        addEmailFieldTextChangedListener()
        viewModel.errorMessage.observe(viewLifecycleOwner, {errorMessage ->
            binding.etPassword.error = errorMessage
        })
        addPasswordFieldTextChangedListener()
    }

    private fun addEmailFieldTextChangedListener() = with(binding) {
        etEmail.addTextChangedListener { editable ->
            val email = editable.toString().trim()
            val password = etPassword.text.toString().trim()

            viewModel.loginDataChanged(email, password)
        }
    }

    private fun addPasswordFieldTextChangedListener() = with(binding) {
        etEmail.addTextChangedListener { editable ->
            val password = editable.toString().trim()
            val email = etEmail.text.toString().trim()
            viewModel.setError(password, getString(R.string.login_fragment_login_need_more_than_8_symbols))
            viewModel.loginDataChanged(email, password)
        }
    }

    private fun setButtonClickListener() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainPageFragment())
        }
    }


}
