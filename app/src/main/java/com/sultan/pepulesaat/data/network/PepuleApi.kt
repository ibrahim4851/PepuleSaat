package com.sultan.pepulesaat.data.network

import com.sultan.pepulesaat.data.models.AddToCartModel
import com.sultan.pepulesaat.data.models.BaseResponseDTO
import com.sultan.pepulesaat.data.models.CategoriesResponseDTO
import com.sultan.pepulesaat.data.models.DeleteFromCartModel
import com.sultan.pepulesaat.data.models.ProductDetailDTO
import com.sultan.pepulesaat.data.models.ProductsResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PepuleApi {

    @GET("get_products.php")
    suspend fun getProducts(): ProductsResponseDTO

    @GET("get_products_by_category.php")
    suspend fun getProductsByCategory(@Query("id") category: String): ProductsResponseDTO

    @GET("get_cart_products.php")
    suspend fun getCartProducts(@Query("userId") userId: String): ProductsResponseDTO

    @GET("get_sale_products.php")
    suspend fun getSaleProducts(): ProductsResponseDTO

    @GET("search_product.php")
    suspend fun searchProduct(@Query("query") query: String): ProductsResponseDTO

    @GET("get_categories.php")
    suspend fun getCategories(): CategoriesResponseDTO

    @GET("get_product_detail.php")
    suspend fun getProduct(@Query("id") productId: Int): ProductDetailDTO

    @POST("add_to_cart.php")
    suspend fun addToCart(@Body addToCartModel: AddToCartModel): BaseResponseDTO

    @POST("delete_from_cart.php")
    suspend fun deleteFromCart(@Body deleteFromCartModel: DeleteFromCartModel): BaseResponseDTO

    @POST("clear_cart.php")
    suspend fun clearCart(@Body userId: String): BaseResponseDTO

}