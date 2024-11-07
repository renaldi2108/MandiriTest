package id.renaldi.mandiritest.ui.wishlist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import id.renaldi.mandiritest.R
import com.oyamo.dooka_app.core.util.UiEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.core.presentation.theme.Pink40
import id.renaldi.mandiritest.data.local.wish_list.mapper.toDomain
import id.renaldi.mandiritest.data.local.wish_list.mapper.toProduct
import id.renaldi.mandiritest.domain.model.Wishlist
import id.renaldi.mandiritest.ui.wishlist.component.WishlistItem
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun WishlistScreen(
    navigator: DestinationsNavigator,
    viewModel: WishlistViewModel = hiltViewModel()
) {

    val wishlistItems = viewModel.wishlistItems.observeAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        backgroundColor = Color.White,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                elevation = 1.dp,
                backgroundColor = Color.White,
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp),
                        text = "Wishlist",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.deleteAllWishlist()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn {
            items(wishlistItems.value) { wishlist ->
                WishlistItem(
                    wishlist = wishlist.toDomain(), modifier = Modifier
                        .fillMaxWidth()
                        .height(135.dp)
                        .padding(8.dp),
                    viewModel = viewModel,
                    navigator = navigator
                )
            }
        }

        if ((wishlistItems.value.isEmpty() || wishlistItems.value.isEmpty())) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(220.dp),
                    painter = painterResource(id = R.drawable.ic_artwork),
                    contentDescription = null
                )
            }
        }
    }
}