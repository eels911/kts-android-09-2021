package com.example.android.unsplashmobile.ui.loginFragment

import android.app.Activity
import android.app.Activity.*
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.unsplashmobile.R
import com.example.android.unsplashmobile.databinding.FragmentLoginBinding
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginFragmentVM by viewModels()
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (result.resultCode == RESULT_OK && data != null) {
            val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
                ?.createTokenExchangeRequest()
            val exception = AuthorizationException.fromIntent(data)
            when {
                tokenExchangeRequest != null && exception == null ->
                    viewModel.onAuthCodeReceived(tokenExchangeRequest)
                exception != null -> viewModel.onAuthCodeFailed(exception)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewModel()
    }

    private fun bindViewModel() {
        binding.btnLogin.setOnClickListener { viewModel.openLoginPage() }
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, ::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, ::toast)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainPageFragment())
        }
    }
    private fun updateIsLoading(isLoading: Boolean) = with(binding) {
        btnLogin.isVisible = !isLoading
//        loginProgress.isVisible = isLoading
    }

    private fun openAuthPage(intent: Intent) {
        resultLauncher.launch(intent)
    }


    }






