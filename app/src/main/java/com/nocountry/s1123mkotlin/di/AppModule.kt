package com.nocountry.s1123mkotlin.di

import android.app.Application
import androidx.room.Room
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import com.nocountry.s1123mkotlin.screens.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReminderDao(database: AppDatabase): ReminderDao {
        return database.reminderDao()
    }

    @Provides
    @Singleton
    fun provideReminderRepository(reminderDao: ReminderDao, application: Application): ReminderRepository {
        return ReminderRepository(reminderDao, application.applicationContext)
    }
}

