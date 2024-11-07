package id.renaldi.mandiritest.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import id.renaldi.mandiritest.R
import com.oyamo.dooka_app.core.util.UiEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.core.shared.component.LoadingAnimation
import id.renaldi.mandiritest.ui.main.component.Categories
import id.renaldi.mandiritest.ui.main.component.MyTopAppBar
import id.renaldi.mandiritest.ui.main.component.ProductItem
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            MyTopAppBar(viewModel)
        },
    ) {

        val scaffoldState = rememberScaffoldState()
        LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is UiEvents.SnackbarEvent -> {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                    else -> {}
                }
            }
        }

        val productsState = viewModel.productsState.value
        val categories = viewModel.categoriesState.value

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp)
            ) {
                item(span = { GridItemSpan(2) }) {
                    Card(
                        elevation = 0.dp,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = viewModel.bannerImageState.value)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.ic_placeholder)
                                        crossfade(true)
                                    }).build()
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Black Friday Banner"
                        )
                    }

                } // Header for some banner Image

                // Some spacer
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item(span = { GridItemSpan(2) }) {
                    Categories(categories = categories, viewModel = viewModel)
                }

                // Some spacer
                item(span = { GridItemSpan(2) }) {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Actual product items list
                items(productsState.products) { product ->
                    ProductItem(
                        product = product,
                        navigator = navigator,
                        modifier = Modifier
                            .width(150.dp)
                    )
                }
            }

            if (productsState.isLoading) {
                LoadingAnimation(
                    modifier = Modifier.align(Center),
                    circleSize = 16.dp,
                )
            }

            if (productsState.error != null) Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Center)
                    .padding(16.dp),
                text = productsState.error,
                color = Color.Red
            )
        }
    }
}