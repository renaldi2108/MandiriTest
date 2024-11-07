package id.renaldi.mandiritest.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.renaldi.mandiritest.data.local.auth.AuthPreferences
import id.renaldi.mandiritest.data.remote.auth.AuthApiService
import id.renaldi.mandiritest.data.remote.cart.CartApiService
import id.renaldi.mandiritest.data.remote.product.ProductsApiService
import id.renaldi.mandiritest.data.repository.auth.AuthRepository
import id.renaldi.mandiritest.data.repository.auth.AuthRepositoryImpl
import id.renaldi.mandiritest.data.repository.cart.CartRepository
import id.renaldi.mandiritest.data.repository.cart.CartRepositoryImpl
import id.renaldi.mandiritest.data.repository.product.ProductsRepository
import id.renaldi.mandiritest.data.repository.product.ProductsRepositoryImpl
import id.renaldi.mandiritest.data.repository.profile.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        authPreferences: AuthPreferences
    ): AuthRepository = AuthRepositoryImpl(
        authApiService = authApiService,
        authPreferences = authPreferences
    )

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsApiService: ProductsApiService
    ): ProductsRepository = ProductsRepositoryImpl(
        productsApiService
    )

    @Provides
    @Singleton
    fun provideCartRepository(
        cartApiService: CartApiService
    ): CartRepository = CartRepositoryImpl(
        cartApiService
    )

    @Provides
    @Singleton
    fun provideProfileRepository(
        authPreferences: AuthPreferences, gson: Gson
    ): ProfileRepository = ProfileRepository(authPreferences, gson)
}