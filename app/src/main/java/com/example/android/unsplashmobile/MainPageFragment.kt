package com.example.android.unsplashmobile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.unsplashmobile.databinding.FragmentMainPageBinding
import timber.log.Timber

class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    var binding: FragmentMainPageBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("MainFragment onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("MainFragment onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        Timber.d("MainFragment onCreateView()")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("MainFragment onViewCreated()")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.d("MainFragment onViewStateRestored()")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("MainFragment onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("MainFragment onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("MainFragment onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("MainFragment onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("MainFragment onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Timber.d("MainFragment onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("MainFragment onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("MainFragment onDetach()")
    }
}