package com.elektrobit.check24task.product.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.elektrobit.check24task.R
import com.elektrobit.check24task.product.rp.entity.Product
import com.elektrobit.check24task.product.rp.entity.toDate
import com.elektrobit.check24task.product.rp.entity.toText

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private var productImageView: ImageView? = null
    private var productNameTextView: TextView? = null
    private var productPriceTextView: TextView? = null
    private var productRateRatingBar: RatingBar? = null
    private var releaseDateTextView: TextView? = null
    private var productShortDescTextView: TextView? = null
    private var productLongDescTextView: TextView? = null
    private var product: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            product = ProductDetailsFragmentArgs.fromBundle(it).product
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productImageView = view.findViewById(R.id.product_image_view)
        productNameTextView = view.findViewById(R.id.product_name_text_view)
        productPriceTextView = view.findViewById(R.id.product_price_text_view)
        productRateRatingBar = view.findViewById(R.id.product_rate_rating_bar)
        releaseDateTextView = view.findViewById(R.id.date_text_view)
        productShortDescTextView = view.findViewById(R.id.product_short_desc_text_view)
        productLongDescTextView = view.findViewById(R.id.longDescTextView)


        product?.let {
            productImageView?.load(it.imageURL)
            productNameTextView?.text = it.name
            productPriceTextView?.text = it.price.toText()
            productRateRatingBar?.rating = it.rating.toFloat()
            releaseDateTextView?.text = it.releaseDate.toDate()
            productShortDescTextView?.text = it.description
            productLongDescTextView?.text = it.longDescription

        }

    }

}
