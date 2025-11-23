package com.example.anothercoolapplication.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anothercoolapplication.features.userlist.data.UserDao

@Database(entities = [UserEntity::class], version = 2)
abstract class RoomAppDb: RoomDatabase() {

    abstract fun getUserDao(): UserDao

}