package com.example.anothercoolapplication.features.userlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anothercoolapplication.core.data.db.UserEntity
import com.example.anothercoolapplication.features.userlist.data.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {
    var allUsers: MutableLiveData<List<UserEntity>> = MutableLiveData()

    init {
        getAllUsers()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = userDao.getAllUserInfo()
            allUsers.postValue(list)
        }
    }

    fun insertUserInfo(entity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(entity)
            getAllUsers()
        }
    }

    fun updateUserInfo(entity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.updateUser(entity)
            getAllUsers()
        }
    }

    fun deleteUserInfo(entity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteUser(entity)
            getAllUsers()
        }
    }
}