package com.axppress.maccabitest.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.axppress.maccabitest.domain.local.converters.StringListConverter
import com.axppress.maccabitest.domain.model.local.Product

@Database(entities = [Product::class], version = 2, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val products: ProductsDao
}
