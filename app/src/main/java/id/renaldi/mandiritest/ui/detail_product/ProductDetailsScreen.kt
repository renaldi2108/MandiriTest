package id.renaldi.mandiritest.ui.detail_product

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.renaldi.mandiritest.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.core.presentation.theme.Pink40
import id.renaldi.mandiritest.core.presentation.theme.Purple40
import id.renaldi.mandiritest.data.local.wish_list.mapper.toWishlistRating
import id.renaldi.mandiritest.domain.model.Product
import id.renaldi.mandiritest.domain.model.Wishlist
import id.renaldi.mandiritest.ui.detail_product.component.Details
import id.renaldi.mandiritest.ui.wishlist.WishlistViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun ProductDetailsScreen(
    product: Product,
    navigator: DestinationsNavigator,
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val inWishlist = viewModel.inWishlist(product.id).observeAsState().value ?: false

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navigator.popBackStack()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(
                    onClick = {
                        if (inWishlist) {
                            println("deleteFromWishlist")
                            viewModel.deleteFromWishlist(
                                Wishlist(
                                    image = product.image,
                                    title = product.title,
                                    id = product.id,
                                    liked = true,
                                    price = product.price,
                                    description = product.description,
                                    category = product.category,
                                    rating = product.rating.toWishlistRating()
                                )
                            )
                        } else {
                            println("insertFavorite")
                            viewModel.insertFavorite(
                                Wishlist(
                                    image = product.image,
                                    title = product.title,
                                    id = product.id,
                                    liked = true,
                                    price = product.price,
                                    description = product.description,
                                    category = product.category,
                                    rating = product.rating.toWishlistRating()
                                )
                            )
                        }
                    },
                ) {
                    Icon(
                        painter = if (inWishlist) {
                            painterResource(id = R.drawable.ic_heart_fill)
                        } else {
                            painterResource(id = R.drawable.ic_heart)
                        },
                        tint = if (inWishlist) {
                            Pink40
                        } else {
                            Purple40
                        },
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) {
        Details(
            product,
            modifier = Modifier.fillMaxSize()
        )
    }
}