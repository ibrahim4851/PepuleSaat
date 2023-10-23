package com.sultan.pepulesaat.domain.usecase

import android.util.Log
import com.sultan.pepulesaat.data.models.CategoriesResponseDTO
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: PepuleRepository) {

    fun executeGetCategories(): Flow<Resource<List<String>>> = flow {
        try {
            val response = repository.getCategories()
            if(response.status == 200){
                emit(Resource.Success(response.categories))
            }
            else{
                Log.i("123452134", "no category")
                emit(Resource.Error(message = "No Category Found"))
            }
        }
        catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error123412512!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }

}