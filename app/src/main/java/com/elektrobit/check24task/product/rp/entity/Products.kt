package com.elektrobit.check24task.product.rp.entity

data class Products(
    var header: Header,
    var filters: List<String> = emptyList(),
    var products: List<Product> = emptyList()
)


data class Product(

    var name: String,
    var type: String,
    var id: Int,
    var color: String,
    var imageURL: String,
    var colorCode: String,
    var available: Boolean,
    var releaseDate: Int,
    var description: String,
    var longDescription: String,
    var rating: Double,
    var price: Price

)

data class Price(
    var value: Double,
    var currency: String

)

data class Header(
    var headerTitle: String,
    var headerDescription: String
)
