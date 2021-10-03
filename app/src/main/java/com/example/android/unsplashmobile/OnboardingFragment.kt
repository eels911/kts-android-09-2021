package com.example.android.unsplashmobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android.unsplashmobile.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private val binding: FragmentOnboardingBinding by viewBinding(FragmentOnboardingBinding::bind)
    private lateinit var onboardNamesArray: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        onboardNamesArray = resources.getStringArray(R.array.onboard_names)

        val onboardAdapter = OnboardingAdapter(activity as AppCompatActivity, onboardNamesArray.size)
        binding.onboardViewPager.adapter = onboardAdapter

        TabLayoutMediator(binding.tabLayout, binding.onboardViewPager) { tab, position ->
            //Some implementation
        }.attach()
    }
}

