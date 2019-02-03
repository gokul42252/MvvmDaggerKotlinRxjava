package com.ct.mvvmdaggerkotlin.db

import com.ct.mvvmdaggerkotlin.data.User
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllUsers(): Observable<List<User>> {
        return io.reactivex.Observable.fromCallable {
            appDatabase.userDao().getAll()
        }
    }

    fun insertUsers(user: User): Observable<Boolean> {
        return Observable.fromCallable {
            appDatabase.userDao().insert(user)
            true
        }
    }

}