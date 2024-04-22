package com.elektrobit.check24task.product.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.elektrobit.check24task.R
import com.elektrobit.check24task.product.presentation.ProductsListState
import com.elektrobit.check24task.product.presentation.ProductsViewModel
import com.elektrobit.check24task.product.rp.entity.Product
import com.elektrobit.check24task.product.rp.entity.Products
import com.elektrobit.check24task.product.ui.adapter.ProductsListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProductsListFragment : Fragment(R.layout.fragment_products_list),
    ProductsListAdapter.OnItemClickListener {
    private val viewModel: ProductsViewModel by activityViewModel()
    private var productsRecyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var productsAdapter: ProductsListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.isEnabled = true

        swipeRefreshLayout?.setOnRefreshListener {
            viewModel.getProducts()
        }

        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        productsAdapter = ProductsListAdapter(onItemClickListener = this)

        val viewManager = LinearLayoutManager(context)
        productsRecyclerView?.apply {
            layoutManager = viewManager
            adapter = productsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is ProductsListState.Failure -> onFailure(it.failureMessage)
                        ProductsListState.Idle -> onIdle()
                        ProductsListState.Loading -> onLoading()
                        is ProductsListState.Success -> onSuccess(it.data)
                    }
                }
            }
        }
    }

    private fun onSuccess(products: Products) {

        productsAdapter?.updateData(products.products)
        swipeRefreshLayout?.isRefreshing = false
        productsRecyclerView?.visibility = View.VISIBLE
    }

    private fun onFailure(failureMsg: String) {
        Log.d("CHECK24TASK", "ProductsListFragment#onFailure: $failureMsg");
    }

    private fun onIdle() {

    }


    private fun onLoading() {
        swipeRefreshLayout?.isRefreshing = true
    }

    override fun onItemClicked(item: Product) {

    }
}
