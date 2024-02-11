package com.axppress.maccabitest.domain.model.remote

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.axppress.maccabitest.domain.model.local.Product
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Int,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("stock") val stock: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("category") val category: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>,
) : Parcelable {

    fun toModel(): Product = Product(
        id = this.id,
        title = this.title,
        category = this.category,
        price = this.price,
        stock = this.stock,
        thumbnail = this.thumbnail,
    )
}
