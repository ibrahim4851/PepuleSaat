package com.sultan.pepulesaat.domain.usecase

import android.util.Log
import com.sultan.pepulesaat.data.models.ProductDTO
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: PepuleRepository) {

    fun executeGetProducts(): Flow<Resource<List<ProductDTO>>> = flow {
        try {
            val response = repository.getProducts()
            if (response.status == 200){
                emit(Resource.Success(response.products))
            }
            else{
                emit(Resource.Error(message = "No Product Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error123412512!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}