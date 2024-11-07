package id.renaldi.mandiritest.data.local.wish_list

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.renaldi.mandiritest.data.local.wish_list.util.Converters

@TypeConverters(Converters::class)
@Database(entities = [WishlistEntity::class], version = 2)
abstract class WishlistDatabase : RoomDatabase() {
    abstract val wishlistDao: WishlistDao
}