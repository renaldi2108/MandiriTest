package id.renaldi.mandiritest.ui.auth.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.R
import id.renaldi.mandiritest.ui.destinations.AuthDashboardScreenDestination
import id.renaldi.mandiritest.ui.destinations.HomeScreenDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashViewModel = hiltViewModel()
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val scale = remember {
            Animatable(0f)
        }

        val overshootInterpolator = remember {
            OvershootInterpolator(1.5f)
        }

        LaunchedEffect(key1 = true) {
            withContext(Dispatchers.Main) {
                scale.animateTo(
                    targetValue = 1.0f,
                    animationSpec = tween(
                        durationMillis =10,
                        easing = {
                            overshootInterpolator.getInterpolation(it)
                        })
                )
                val eventState = viewModel.eventState.value

                if (eventState) {
                    navigator.popBackStack()
                    navigator.navigate(HomeScreenDestination)
                } else {
                    navigator.popBackStack()
                    navigator.navigate(AuthDashboardScreenDestination)
                }
            }
        }

        Image(
            modifier = Modifier.padding(24.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}