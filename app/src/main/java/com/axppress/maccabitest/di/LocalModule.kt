package com.axppress.maccabitest.di

import android.content.Context
import androidx.room.Room
import com.axppress.maccabitest.domain.local.AppDatabase
import com.axppress.maccabitest.domain.local.converters.StringListConverter

val localModule = org.koin.dsl.module {
    single { createHolidaysDatabase(get<Context>()) }
}

private fun createHolidaysDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, "maccabi_database")
        .fallbackToDestructiveMigration()
        .build().products
