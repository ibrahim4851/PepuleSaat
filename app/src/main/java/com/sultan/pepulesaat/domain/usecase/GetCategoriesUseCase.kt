package com.sultan.pepulesaat.domain.usecase

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
                emit(Resource.Error(message = "Kategori Bulunamadı"))
            }
        }
        catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error123412512!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "İnternete erişilemiyor"))
        }
    }

}