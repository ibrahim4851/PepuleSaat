package com.sultan.pepulesaat.domain.repository

import com.sultan.pepulesaat.data.models.AddToCartModel
import com.sultan.pepulesaat.data.models.BaseResponseDTO
import com.sultan.pepulesaat.data.models.CategoriesResponseDTO
import com.sultan.pepulesaat.data.models.ClearCartModel
import com.sultan.pepulesaat.data.models.DeleteFromCartModel
import com.sultan.pepulesaat.data.models.ProductDetailDTO
import com.sultan.pepulesaat.data.models.ProductsResponseDTO

interface PepuleRepository {

    suspend fun getProducts(): ProductsResponseDTO
    suspend fun getProductsByCategory(category: String): ProductsResponseDTO
    suspend fun getCartProducts(userId: String): ProductsResponseDTO
    suspend fun getSaleProducts(): ProductsResponseDTO
    suspend fun searchProduct(query: String): ProductsResponseDTO
    suspend fun getCategories(): CategoriesResponseDTO
    suspend fun getProduct(productId: Int): ProductDetailDTO
    suspend fun addToCart(addToCartModel: AddToCartModel): BaseResponseDTO
    suspend fun deleteFromCart(deleteFromCartModel: DeleteFromCartModel): BaseResponseDTO
    suspend fun clearCart(clearCartModel: ClearCartModel): BaseResponseDTO

}