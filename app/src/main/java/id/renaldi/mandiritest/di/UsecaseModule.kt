package id.renaldi.mandiritest.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.renaldi.mandiritest.data.local.auth.AuthPreferences
import id.renaldi.mandiritest.data.repository.auth.AuthRepository
import id.renaldi.mandiritest.data.repository.cart.CartRepository
import id.renaldi.mandiritest.data.repository.product.ProductsRepository
import id.renaldi.mandiritest.domain.usecase.auth.AutoLoginUseCase
import id.renaldi.mandiritest.domain.usecase.auth.LoginUseCase
import id.renaldi.mandiritest.domain.usecase.cart.GetCartItemsUseCase
import id.renaldi.mandiritest.domain.usecase.product.GetCategoriesUseCase
import id.renaldi.mandiritest.domain.usecase.product.GetProductsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: AuthRepository
    ): LoginUseCase = LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideAutoLoginUseCase(
        repository: AuthRepository
    ): AutoLoginUseCase = AutoLoginUseCase(repository)

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productsRepository: ProductsRepository
    ): GetProductsUseCase = GetProductsUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        productsRepository: ProductsRepository
    ): GetCategoriesUseCase = GetCategoriesUseCase(productsRepository)

    @Provides
    @Singleton
    fun provideGetCartItemsUseCase(
        cartRepository: CartRepository,
        authPreferences: AuthPreferences,
        gson: Gson
    ): GetCartItemsUseCase = GetCartItemsUseCase(cartRepository, authPreferences, gson)
}