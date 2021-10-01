package com.example.android.unsplashmobile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class StateLoginVM(
    private val state: SavedStateHandle
) : ViewModel() {
    val liveData = state.getLiveData("liveData", "")

    fun saveState() {
        state.set("liveData", liveData.value)
    }
}
