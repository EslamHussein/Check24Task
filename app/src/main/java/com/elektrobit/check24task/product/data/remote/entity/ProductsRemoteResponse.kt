package com.elektrobit.check24task.product.data.remote.entity

import com.google.gson.annotations.SerializedName


data class ProductsRemoteResponse(
    @SerializedName("header") val header: HeaderRemote,
    @SerializedName("filters") val filters: List<String> = emptyList(),
    @SerializedName("products") val products: List<ProductRemote> = emptyList()
)


data class ProductRemote(

    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: Int,
    @SerializedName("color") val color: String,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("colorCode") val colorCode: String,
    @SerializedName("available") val available: Boolean,
    @SerializedName("releaseDate") val releaseDate: Int,
    @SerializedName("description") val description: String,
    @SerializedName("longDescription") val longDescription: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("price") val price: PriceRemote

)

data class PriceRemote(
    @SerializedName("value") val value: Double,
    @SerializedName("currency") val currency: String

)

data class HeaderRemote(
    @SerializedName("headerTitle") val headerTitle: String,
    @SerializedName("headerDescription") val headerDescription: String
)
