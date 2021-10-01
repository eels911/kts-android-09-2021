package com.example.android.unsplashmobile

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.unsplashmobile.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    var binding: FragmentLoginBinding? = null
    var validEmail = false
    var validPassword = false
    lateinit var viewModel: StateLoginVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(requireActivity().application, this)
        ).get(StateLoginVM::class.java)

        setButtonClickListener()

        binding?.etEmail?.addTextChangedListener {
            val emailString = binding?.etEmail?.text?.toString()!!
            validEmail = checkEmail(emailString)
            if (validEmail) binding?.typeEmail?.isErrorEnabled = false
            setButtonClickListener()
        }

        binding?.etPassword?.addTextChangedListener {
            val passwordString = binding?.etPassword?.text?.toString()!!
            validPassword = passwordString.length >= 8
            if (validPassword) binding?.typePassword?.isErrorEnabled = false
            setButtonClickListener()
        }
    }

    fun setButtonClickListener() {
        if (validEmail && validPassword) {
            binding?.btnLogin?.setBackgroundResource(R.color.black)
            binding?.btnLogin?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding?.btnLogin?.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainPageFragment())
            }
        } else {
            binding?.btnLogin?.setBackgroundResource(R.color.grey_button_color)
            binding?.btnLogin?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding?.btnLogin?.setOnClickListener {
                if (!validEmail) {
                    binding?.typeEmail?.error =
                        getString(R.string.login_fragment_login_enter_correct_email)
                }
                if (!validPassword) {
                    binding?.typePassword?.error =
                        getString(R.string.login_fragment_login_need_more_than_8_symbols)
                }
            }
        }
    }

    fun checkEmail(emailString: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(emailString).matches()

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (::viewModel.isInitialized) {
            viewModel.saveState()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
