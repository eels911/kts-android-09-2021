package com.example.android.unsplashmobile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.android.unsplashmobile.databinding.FragmentOnboardingBinding
import timber.log.Timber

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    var binding: FragmentOnboardingBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("OnBoardingFragment onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("OnBoardingFragment onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        Timber.d("OnBoardingFragment onCreateView()")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("OnBoardingFragment onViewCreated()")
        binding?.tvSkip?.setOnClickListener {
            findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.d("OnBoardingFragment onViewStateRestored()")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("OnBoardingFragment onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("OnBoardingFragment onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("OnBoardingFragment onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("OnBoardingFragment onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("OnBoardingFragment onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Timber.d("OnBoardingFragment onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("OnBoardingFragment onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("OnBoardingFragment onDetach()")
    }

}

