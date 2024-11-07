package id.renaldi.mandiritest.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.renaldi.mandiritest.data.local.wish_list.WishlistDatabase
import id.renaldi.mandiritest.data.local.wish_list.util.Constant.WISHLIST_DATABASE
import id.renaldi.mandiritest.data.local.wish_list.util.Converters
import id.renaldi.mandiritest.data.repository.wish_list.WishlistRepository
import id.renaldi.mandiritest.data.repository.wish_list.WishlistRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WishListModule {

    @Provides
    @Singleton
    fun provideConverters(gson: Gson) = Converters(gson)

    @Provides
    @Singleton
    fun provideWishlistDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): WishlistDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WishlistDatabase::class.java,
            WISHLIST_DATABASE
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(converters)
            .build()
    }

    @Provides
    @Singleton
    fun provideWishlistRepository(
        wishlistDatabase: WishlistDatabase
    ): WishlistRepository = WishlistRepositoryImpl(wishlistDatabase.wishlistDao)
}