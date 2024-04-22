package com.elektrobit.check24task.product.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.elektrobit.check24task.R
import com.elektrobit.check24task.product.rp.entity.Price
import com.elektrobit.check24task.product.rp.entity.Product

class ProductsListAdapter(
    private var data: List<Product> = emptyList(),
    private var onItemClickListener: OnItemClickListener?
) :
    RecyclerView.Adapter<BaseViewHolder<Product>>() ,Filterable{

    var filtered: List<Product> = data

    enum class Type(value: Int) { Available(0), NotAvailable(1) }

    override fun getItemViewType(position: Int): Int {
        return data[position].getHolderType().ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Product> {

        when (viewType) {
            Type.Available.ordinal -> {
                val itemView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.product_item_aviailable, parent, false)
                return AvailableViewHolder(itemView)
            }

            else -> {

                val itemView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.product_item_not_aviailable, parent, false)
                return NotAvailableViewHolder(itemView)

            }
        }

    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Product>, position: Int) {
        val product = data[position]

        holder.bind(product)

    }


    inner class AvailableViewHolder(view: View) : BaseViewHolder<Product>(view) {
        val productImageView: ImageView
        val productNameTextView: TextView
        val productDescriptionTextView: TextView
        val productReleaseDataTextView: TextView
        val priceTextView: TextView
        val productRateRatingBar: RatingBar

        init {
            productImageView = view.findViewById(R.id.product_image_view)
            productNameTextView = view.findViewById(R.id.product_name_text_view)
            productDescriptionTextView = view.findViewById(R.id.product_description_text_view)
            productReleaseDataTextView = view.findViewById(R.id.date_text_view)
            priceTextView = view.findViewById(R.id.product_price_text_view)
            productRateRatingBar = view.findViewById(R.id.product_rate_rating_bar)
        }

        override fun bind(item: Product) {
            productNameTextView.text = item.name
            productDescriptionTextView.text = item.description
            productReleaseDataTextView.text = item.releaseDate.toString() // Format date
            priceTextView.text = item.price.toText()
            productRateRatingBar.rating = item.rating.toFloat()

            productImageView.load(item.imageURL) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(item)
            }
        }
    }


    inner class NotAvailableViewHolder(view: View) : BaseViewHolder<Product>(view) {
        val productImageView: ImageView
        val productNameTextView: TextView
        val productDescriptionTextView: TextView
        val productRateRatingBar: RatingBar

        init {
            productImageView = view.findViewById(R.id.product_image_view)
            productNameTextView = view.findViewById(R.id.product_name_text_view)
            productDescriptionTextView = view.findViewById(R.id.product_description_text_view)
            productRateRatingBar = view.findViewById(R.id.product_rate_rating_bar)
        }

        override fun bind(item: Product) {
            productNameTextView.text = item.name
            productDescriptionTextView.text = item.description
            productRateRatingBar.rating = item.rating.toFloat()

            productImageView.load(item.imageURL) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(item)
            }
        }
    }

    fun updateData(updatedList: List<Product>) {

        val diffResult = DiffUtil.calculateDiff(
            ProductsDiffCallback(
                updatedList,
                data
            )
        )
        this.data = updatedList
        diffResult.dispatchUpdatesTo(this)

    }

    interface OnItemClickListener {
        fun onItemClicked(item: Product)
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}

private fun Product.getHolderType(): ProductsListAdapter.Type {
    return if (available) ProductsListAdapter.Type.Available else ProductsListAdapter.Type.NotAvailable
}

private fun Price.toText(): String {
    return "$value $currency"
}
