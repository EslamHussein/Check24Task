package com.elektrobit.check24task.product.rp.entity

import android.os.Parcelable
import android.text.format.DateFormat
import kotlinx.parcelize.Parcelize
import java.util.Calendar
import java.util.Locale

data class Products(
    var header: Header,
    var filters: List<String> = emptyList(),
    var products: List<Product> = emptyList()
)

@Parcelize
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

) : Parcelable

@Parcelize
data class Price(
    var value: Double,
    var currency: String

) : Parcelable

data class Header(
    var headerTitle: String,
    var headerDescription: String
)


fun Price.toText(): String {
    return "$value $currency"
}

fun Int.toDate(): String {

    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = this * 1000L
    return DateFormat.format("dd.MM.yyyy", calendar).toString()

}
