package com.axppress.maccabitest.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axppress.maccabitest.domain.model.local.Product

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)
}
