package com.elektrobit.check24task.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elektrobit.check24task.product.rp.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// todo introduce domain layer
class ProductsViewModel(private val productsRepository: ProductsRepository) : ViewModel() {

    private val _state: MutableStateFlow<ProductsListState> =
        MutableStateFlow(ProductsListState.Idle)

    val state: StateFlow<ProductsListState> = _state.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() = viewModelScope.launch {

        productsRepository.getProducts()
            .onStart {
                _state.update { ProductsListState.Loading }
            }
            .catch { error ->
                _state.update {
                    ProductsListState.Failure(error.toString()) // TODO Add error handler
                }
            }.collectLatest { products ->
                _state.update { ProductsListState.Success(products) }
            }


    }
}
