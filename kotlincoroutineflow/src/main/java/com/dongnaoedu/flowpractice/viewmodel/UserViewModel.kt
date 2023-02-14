package com.dongnaoedu.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dongnaoedu.flowpractice.db.AppDatabase
import com.dongnaoedu.flowpractice.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class UserViewModel(app: Application) : AndroidViewModel(app) {


//    fun insert(bid: String, firstName: String, lastName: String) {
//        viewModelScope.launch {
//            AppDatabase.getInstance(getApplication())
//                .userDao()
//                .insert(User(bid.toInt(), firstName, lastName))
//            Log.d("ning", "insert user:$uid")
//        }
//    }

    fun insert(user: User) {
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication())
                .userDao()
                .insert(user)
            Log.d("ning", "插入书籍id:${user.bid}")
        }
    }


    fun getAll(): Flow<List<User>> {
        return AppDatabase.getInstance(getApplication())
            .userDao()
            .getAll()
            .catch { e -> e.printStackTrace() }
            .flowOn(Dispatchers.IO)
    }

}