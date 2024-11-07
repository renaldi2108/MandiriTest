package id.renaldi.mandiritest.data.repository.wish_list

import androidx.lifecycle.LiveData
import id.renaldi.mandiritest.data.local.wish_list.WishlistDao
import id.renaldi.mandiritest.data.local.wish_list.WishlistEntity
import id.renaldi.mandiritest.data.local.wish_list.mapper.toEntity
import id.renaldi.mandiritest.domain.model.Wishlist

interface WishlistRepository {
    suspend fun insertToWishlist(wishlist: Wishlist)
    fun getWishlist(): LiveData<List<WishlistEntity>>
    fun inWishlist(id: Int): LiveData<Boolean>
    fun getOneWishlistItem(id: Int): LiveData<WishlistEntity?>
    suspend fun deleteOneWishlist(wishlist: Wishlist)
    suspend fun deleteAllWishlist()
}

class WishlistRepositoryImpl(
    private val wishlistDao: WishlistDao
) : WishlistRepository {
    override suspend fun insertToWishlist(wishlist: Wishlist) {
        wishlistDao.insertToWishlist(wishlist.toEntity())
    }

    override fun getWishlist(): LiveData<List<WishlistEntity>> {
        return wishlistDao.getWishlist()
    }

    override fun inWishlist(id: Int): LiveData<Boolean> {
        return wishlistDao.inWishlist(id)
    }

    override fun getOneWishlistItem(id: Int): LiveData<WishlistEntity?> {
        return wishlistDao.getOneWishlistItem(id)
    }

    override suspend fun deleteOneWishlist(wishlist: Wishlist) {
        wishlistDao.deleteAWishlist(wishlist.toEntity())
    }

    override suspend fun deleteAllWishlist() {
        wishlistDao.deleteAllWishlist()
    }
}