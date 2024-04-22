package com.elektrobit.check24task.product.mapper

import com.elektrobit.check24task.product.data.remote.entity.HeaderRemote
import com.elektrobit.check24task.product.data.remote.entity.PriceRemote
import com.elektrobit.check24task.product.data.remote.entity.ProductRemote
import com.elektrobit.check24task.product.data.remote.entity.ProductsRemoteResponse
import com.elektrobit.check24task.product.rp.entity.Header
import com.elektrobit.check24task.product.rp.entity.Price
import com.elektrobit.check24task.product.rp.entity.Product
import com.elektrobit.check24task.product.rp.entity.Products

fun ProductsRemoteResponse.toUiEntity(): Products {

    return Products(header.toUiEntity(), filters, products.map { it.toUiEntity() })

}


fun HeaderRemote.toUiEntity(): Header {
    return Header(headerTitle, headerDescription)
}

fun PriceRemote.toUiEntity(): Price {
    return Price(value, currency)
}

fun ProductRemote.toUiEntity(): Product {

    return Product(
        name,
        type,
        id,
        color,
        imageURL,
        colorCode,
        available,
        releaseDate,
        description,
        longDescription,
        rating,
        price.toUiEntity()
    )
}
