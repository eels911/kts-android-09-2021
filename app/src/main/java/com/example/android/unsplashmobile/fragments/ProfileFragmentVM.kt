package com.example.android.unsplashmobile.fragments

import androidx.lifecycle.*
import com.example.android.unsplashmobile.data.auth.AuthUser
import kotlinx.coroutines.launch

//class ProfileFragmentVM: ViewModel() {
//
//    private val currentUserMutableLiveData = MutableLiveData<AuthUser>()
//    private val networkingRepository = NetworkingRepository()
//
//    val currentUserLiveData: LiveData<AuthUser>
//        get() = currentUserMutableLiveData
//
//    init {
//        loadAuthUser()
//    }
//    private fun loadAuthUser(){
//        viewModelScope.launch {
//                currentUserMutableLiveData.postValue(networkingRepository.getInfoAboutAuthorizedUser())
//            }
//        }
//    }

