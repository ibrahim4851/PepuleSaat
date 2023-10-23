package com.sultan.pepulesaat.domain.usecase

import com.sultan.pepulesaat.data.models.AddToCartModel
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val repository: PepuleRepository) {

    fun executeAddToCart(addToCartModel: AddToCartModel): Flow<Resource<String>> = flow {
        try {
            val response = repository.addToCart(addToCartModel)
            if (response.status == 200){
                emit(Resource.Success(data = response.message))
            }
            else if (response.status == 400){
                emit(Resource.Error(message = response.message))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error123412512!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}