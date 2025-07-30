package com.example.fooddeliveryapp_task.presentation.home.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp_task.R
import com.example.fooddeliveryapp_task.domain.usecases.GetFoodScreenDataUseCase
import com.example.fooddeliveryapp_task.presentation.home.model.CartItem
import com.example.fooddeliveryapp_task.presentation.home.model.HomeUiState
import com.example.fooddeliveryapp_task.presentation.home.model.PromoBannerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFoodScreenDataUseCase: GetFoodScreenDataUseCase
) : ViewModel() {
    private val _promoItems = MutableStateFlow<List<PromoBannerItem>>(emptyList())
    val promoItems: StateFlow<List<PromoBannerItem>> = _promoItems.asStateFlow()

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    // Expose calculated totals for the order summary
    val subtotal: StateFlow<Double> = MutableStateFlow(0.0)
    val deliveryFee: StateFlow<Double> = MutableStateFlow(10.0) // Example fixed fee
    val tax: StateFlow<Double> = MutableStateFlow(0.0)
    val totalOrderPrice: StateFlow<Double> = MutableStateFlow(0.0)



    init {
        initializePromoItems()
        fetchHomeScreenData()
        loadInitialCartItems()
        observeCartChanges()
    }
    private fun observeCartChanges() {
        viewModelScope.launch {
            _cartItems.collect { items ->
                val currentSubtotal = items.sumOf { it.totalPrice }
                (subtotal as MutableStateFlow).value = currentSubtotal
                // Example tax calculation (e.g., 5% of subtotal)
                val currentTax = currentSubtotal * 0.05
                (tax as MutableStateFlow).value = currentTax
                (totalOrderPrice as MutableStateFlow).value = currentSubtotal + deliveryFee.value + currentTax
            }
        }
    }
    fun incrementQuantity(itemId: String) {
        updateQuantity(itemId, 1)
    }

    fun decrementQuantity(itemId: String) {
        updateQuantity(itemId, -1)
    }

    fun updateQuantity(itemId: String, change: Int) {
        _cartItems.update { currentItems ->
            currentItems.map { item ->
                if (item.id == itemId) {
                    val newQuantity = item.quantity + change
                    if (newQuantity > 0) {
                        item.copy(quantity = newQuantity)
                    } else {
                       item.copy(quantity = 1)
                    }
                } else {
                    item
                }
            }.filter { it.quantity > 0 }
        }
    }

    private fun loadInitialCartItems() {
        _cartItems.value = listOf(
            CartItem("pizza_01", "Pizza", 249.0, R.drawable.ic_burger, 2),
            CartItem("burger_01", "Burger", 149.0, R.drawable.ic_juice, 1),
            CartItem("pasta_01", "Pasta", 199.0, R.drawable.ic_rice, 2)
        )
    }
    private fun initializePromoItems() {
        val staticPromoItemsList: List<PromoBannerItem> = listOf(
            PromoBannerItem(
                title = "Amazing Deals This Summer!",
                buttonText = "View Offers",
                imageRes = R.drawable.ic_banner_mage // Replace with your actual drawable
            ),
            PromoBannerItem(
                title = "Fresh & Fast Food Delivered",
                buttonText = "Order Now",
                imageRes = R.drawable.ic_burger      // Replace with your actual drawable
            ),
            PromoBannerItem(
                title = "Healthy Juices, Happy You",
                buttonText = "Discover More",
                imageRes = R.drawable.ic_juice       // Replace with your actual drawable
            )
        )
        _promoItems.value = staticPromoItemsList
    }
    fun fetchHomeScreenData() {

        _uiState.value = HomeUiState.Loading

        getFoodScreenDataUseCase() // This should return Flow<Result<FoodScreenData>>
            .onEach { result ->
                result.fold(
                    onSuccess = { data ->
                        _uiState.value = HomeUiState.Success(data)
                        Log.d("HomeViewModel", "Successfully fetched home screen data: $data")
                    },
                    onFailure = { throwable ->
                        _uiState.value =
                            HomeUiState.Error(throwable.message ?: "Unknown error occurred")
                        Log.e("HomeViewModel", "Error fetching home screen data", throwable)
                    }
                )
            }
            .catch { e ->
                _uiState.value =
                    HomeUiState.Error(e.message ?: "An unexpected error occurred in the data flow")
                Log.e("HomeViewModel", "Exception in home screen data flow", e)
            }
            .launchIn(viewModelScope)
    }

}


