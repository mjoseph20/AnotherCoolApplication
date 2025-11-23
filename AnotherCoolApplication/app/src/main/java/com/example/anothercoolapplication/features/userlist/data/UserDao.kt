package com.example.anothercoolapplication.features.userlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.anothercoolapplication.core.data.db.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    suspend fun getAllUserInfo(): List<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)


}