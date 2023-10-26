package com.sultan.pepulesaat.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.firebase.auth.FirebaseAuth
import com.sultan.pepulesaat.data.network.PepuleApi
import com.sultan.pepulesaat.data.repository.PepuleRepositoryImpl
import com.sultan.pepulesaat.data.room.dao.FavoriteDao
import com.sultan.pepulesaat.data.room.database.FavoriteDatabase
import com.sultan.pepulesaat.data.room.repository.FavoriteRepositoryImpl
import com.sultan.pepulesaat.domain.repository.FavoriteRepository
import com.sultan.pepulesaat.domain.repository.PepuleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        "favorite_db"
    ).build()


    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository =
        FavoriteRepositoryImpl(dao = favoriteDao)

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase) = favoriteDatabase.dao

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("store", "pepulesaat")
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePepuleApi(@ApplicationContext context: Context): PepuleApi {
        return Retrofit.Builder()
            .baseUrl("https://api.canerture.com/ecommerce/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context))
            .build()
            .create(PepuleApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPepuleRepository(api: PepuleApi): PepuleRepository {
        return PepuleRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    }

}