package com.nocountry.s1123mkotlin.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nocountry.s1123mkotlin.UserProfile.UserProfile
import com.nocountry.s1123mkotlin.UserProfile.UserProfileDao
import com.nocountry.s1123mkotlin.screens.ReminderDao
import com.nocountry.s1123mkotlin.screens.ReminderEntity

@Database(entities = [UserProfile::class, ReminderEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        // Define la migración
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Define las operaciones de migración aquí
                // Por ejemplo, si deseas agregar una nueva columna a la tabla "reminder," puedes hacerlo aquí.
                database.execSQL("ALTER TABLE reminder ADD COLUMN nueva_columna TEXT")
            }
        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Recordatorios_medicamentos"
                )
                    .addMigrations(MIGRATION_1_2) // Agrega la migración aquí
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
