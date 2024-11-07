package id.renaldi.mandiritest.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.renaldi.mandiritest.core.shared.util.Constants.BASE_URL
import id.renaldi.mandiritest.data.remote.auth.AuthApiService
import id.renaldi.mandiritest.data.remote.cart.CartApiService
import id.renaldi.mandiritest.data.remote.product.ProductsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthApiService(): AuthApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideProductsApiService(): ProductsApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductsApiService::class.java)

    @Provides
    @Singleton
    fun provideCartApiService(): CartApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CartApiService::class.java)
}