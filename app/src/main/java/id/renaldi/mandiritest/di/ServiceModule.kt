package id.renaldi.mandiritest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.renaldi.mandiritest.data.remote.auth.AuthApiService
import id.renaldi.mandiritest.data.remote.cart.CartApiService
import id.renaldi.mandiritest.data.remote.product.ProductsApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthApiService(
        retrofit: Retrofit
    ): AuthApiService = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideProductsApiService(
        retrofit: Retrofit
    ): ProductsApiService = retrofit.create(ProductsApiService::class.java)

    @Provides
    @Singleton
    fun provideCartApiService(
        retrofit: Retrofit
    ): CartApiService = retrofit.create(CartApiService::class.java)
}