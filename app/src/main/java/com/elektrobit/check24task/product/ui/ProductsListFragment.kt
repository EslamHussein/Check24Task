package com.elektrobit.check24task.product.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.elektrobit.check24task.R
import com.elektrobit.check24task.product.presentation.ProductsListState
import com.elektrobit.check24task.product.presentation.ProductsViewModel
import com.elektrobit.check24task.product.rp.entity.Product
import com.elektrobit.check24task.product.rp.entity.Products
import com.elektrobit.check24task.product.ui.adapter.ProductsListAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProductsListFragment : Fragment(R.layout.fragment_products_list),
    ProductsListAdapter.OnItemClickListener {
    private val viewModel: ProductsViewModel by activityViewModel()
    private var productsRecyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var productsAdapter: ProductsListAdapter? = null
    private var filterTabLayout: TabLayout? = null
    private var tileTextView: TextView? = null
    private var subTileTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        filterTabLayout = view.findViewById(R.id.filterTabLayout)
        tileTextView = view.findViewById(R.id.products_title_text_view)
        subTileTextView = view.findViewById(R.id.products_sub_title_text_view)
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
                        ProductsListState.Loading -> onLoading()
                        is ProductsListState.Success -> onSuccess(it.data)
                        ProductsListState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun onSuccess(products: Products) {
        filterTabLayout?.removeAllTabs()
        products.filters.forEach { filterItem ->
            filterTabLayout?.let {
                it.addTab(it.newTab().setText(filterItem))
            }
        }
        tileTextView?.text = products.header.headerTitle
        subTileTextView?.text = products.header.headerDescription
        productsAdapter?.updateData(products.products)
        swipeRefreshLayout?.isRefreshing = false
        productsRecyclerView?.visibility = View.VISIBLE
    }

    private fun onFailure(failureMsg: String) {
        Log.d("CHECK24TASK", "ProductsListFragment#onFailure: $failureMsg");
    }

    private fun onLoading() {
        swipeRefreshLayout?.isRefreshing = true
    }

    override fun onItemClicked(item: Product) {

        val action =
            ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(item)

        view?.findNavController()?.navigate(action)


    }
}
