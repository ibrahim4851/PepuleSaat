package com.sultan.pepulesaat.data.repository

import com.sultan.pepulesaat.data.models.AddToCartModel
import com.sultan.pepulesaat.data.models.BaseResponseDTO
import com.sultan.pepulesaat.data.models.CategoriesResponseDTO
import com.sultan.pepulesaat.data.models.ClearCartModel
import com.sultan.pepulesaat.data.models.DeleteFromCartModel
import com.sultan.pepulesaat.data.models.ProductDetailDTO
import com.sultan.pepulesaat.data.models.ProductsResponseDTO
import com.sultan.pepulesaat.data.network.PepuleApi
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import javax.inject.Inject

class PepuleRepositoryImpl @Inject constructor(private val api: PepuleApi) : PepuleRepository {

    override suspend fun getProducts(): ProductsResponseDTO {
        return api.getProducts()
    }

    override suspend fun getProductsByCategory(category: String): ProductsResponseDTO {
        return api.getProductsByCategory(category)
    }

    override suspend fun getCartProducts(userId: String): ProductsResponseDTO {
        return api.getCartProducts(userId)
    }

    override suspend fun getSaleProducts(): ProductsResponseDTO {
        return api.getSaleProducts()
    }

    override suspend fun searchProduct(query: String): ProductsResponseDTO {
        return api.searchProduct(query)
    }

    override suspend fun getCategories(): CategoriesResponseDTO {
        return api.getCategories()
    }

    override suspend fun getProduct(productId: Int): ProductDetailDTO {
        return api.getProduct(productId)
    }

    override suspend fun addToCart(addToCartModel: AddToCartModel): BaseResponseDTO {
        return api.addToCart(addToCartModel)
    }

    override suspend fun deleteFromCart(deleteFromCartModel: DeleteFromCartModel): BaseResponseDTO {
        return api.deleteFromCart(deleteFromCartModel)
    }

    override suspend fun clearCart(clearCartModel: ClearCartModel): BaseResponseDTO {
        return api.clearCart(clearCartModel)
    }
}