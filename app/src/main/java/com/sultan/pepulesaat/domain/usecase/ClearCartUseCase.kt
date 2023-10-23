package com.sultan.pepulesaat.domain.usecase

import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(private val repository: PepuleRepository) {

    fun executeClearCart(userId: String): Flow<Resource<String>> = flow {
        try {
            val response = repository.clearCart(userId)
            if (response.status == 200){
                emit(Resource.Success(response.message))
            }
            else {
                emit(Resource.Error(message = "An Error Occurred While Clearing the Cart"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error123412512!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}