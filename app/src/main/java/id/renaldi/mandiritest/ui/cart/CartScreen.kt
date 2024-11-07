package id.renaldi.mandiritest.ui.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.oyamo.dooka_app.core.util.UiEvents
import id.renaldi.mandiritest.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.core.shared.component.LoadingAnimation
import id.renaldi.mandiritest.domain.model.cart.CartProduct
import id.renaldi.mandiritest.ui.cart.component.CartItem
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun CartScreen(
    navigator: DestinationsNavigator,
    viewModel: CartViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

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
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "My Cart",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
            )
        }
    ) {
        LazyColumn {
            items(state.cartItems) { cartItem ->
                CartItem(
                    cartItem = cartItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(4.dp),
                )
            }
            item {
                if (state.cartItems.isNotEmpty()) {
                    Column(Modifier.padding(12.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${state.cartItems.size} items")
                            Text(
                                text = "${state.cartItems.sumOf { (it.price * it.quantity) }}",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Shipping fee")
                            Text(
                                text = "$60.00", color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Total")
                            Text(
                                text = "$${
                                    state.cartItems.sumOf { (it.price * it.quantity) } + 60.00
                                }", color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                //viewModel.loginUser()
                            },
                            shape = RoundedCornerShape(8)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp), text = "Checkout", textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        if (state.isLoading) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingAnimation(
                    circleSize = 16.dp,
                )
            }
        }

        if (state.error != null) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = state.error,
                    color = Color.Red
                )
            }
        }

        if (state.cartItems.isEmpty() && state.cartItems == null) {
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