package com.example.anothercoolapplication.core.di

import android.app.Application
import androidx.room.Room
import com.example.anothercoolapplication.core.data.db.RoomAppDb
import com.example.anothercoolapplication.features.userlist.data.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppDatabase(context: Application): RoomAppDb {
        return Room.databaseBuilder(
                context,
                RoomAppDb::class.java,
                "AppDB"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Singleton
    @Provides
    fun getUserDao(appDb: RoomAppDb): UserDao {
        return appDb.getUserDao()
    }
}