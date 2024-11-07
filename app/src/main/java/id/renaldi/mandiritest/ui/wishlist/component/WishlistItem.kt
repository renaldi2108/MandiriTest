package id.renaldi.mandiritest.ui.wishlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.R
import id.renaldi.mandiritest.core.presentation.theme.Pink40
import id.renaldi.mandiritest.domain.model.Wishlist
import id.renaldi.mandiritest.ui.wishlist.WishlistViewModel

@Composable
fun WishlistItem(
    wishlist: Wishlist,
    modifier: Modifier = Modifier,
    viewModel: WishlistViewModel,
    navigator: DestinationsNavigator
) {
    Card(
        modifier = modifier.clickable {
//            navigator.navigate(ProductDetailsScreenDestination(wishlist.toProduct()))
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = wishlist.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            crossfade(true)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
            ) {
                Text(
                    text = wishlist.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "$${wishlist.price}",
                    color = Color.Black,
                    fontSize = 22.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light
                )
                IconButton(
                    onClick = {
                        viewModel.deleteFromWishlist(wishlist)
                    },
                    modifier = Modifier.align(End),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart_fill),
                        tint = Pink40,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}