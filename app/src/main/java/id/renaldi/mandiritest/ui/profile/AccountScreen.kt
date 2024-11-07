package id.renaldi.mandiritest.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import id.renaldi.mandiritest.core.presentation.theme.Pink40
import id.renaldi.mandiritest.core.presentation.theme.Purple40
import id.renaldi.mandiritest.domain.model.Account
import id.renaldi.mandiritest.domain.model.User
import id.renaldi.mandiritest.ui.destinations.AuthDashboardScreenDestination
import id.renaldi.mandiritest.ui.destinations.LoginScreenDestination
import id.renaldi.mandiritest.ui.profile.component.MenuItem
import id.renaldi.mandiritest.ui.profile.component.UserItem
import id.renaldi.mandiritest.ui.profile.util.Constants.accountItems
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user = viewModel.profileState.value

    LaunchedEffect(key1 = true) {
        viewModel.logoutFlow.collectLatest { isLogout ->
            if(isLogout){
                navigator.navigate(
                    AuthDashboardScreenDestination.route
                )
            }
        }
    }

    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(
                elevation = 1.dp,
                backgroundColor = Color.White,
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "My Profile",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
            )
        }
    ) {
        LazyColumn {
            item {
                UserItem(
                    user = user,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(4.dp),
                )
            }
            items(accountItems) { item ->
                MenuItem(item = item)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        viewModel.signOut()
                    },
                    shape = RoundedCornerShape(8)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp), text = "Sign Out", textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}