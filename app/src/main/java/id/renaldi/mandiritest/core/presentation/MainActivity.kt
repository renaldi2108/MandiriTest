package id.renaldi.mandiritest.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import id.renaldi.mandiritest.core.presentation.theme.MandiriTestTheme
import id.renaldi.mandiritest.core.shared.component.CustomScaffold
import id.renaldi.mandiritest.ui.NavGraphs
import id.renaldi.mandiritest.ui.destinations.AccountScreenDestination
import id.renaldi.mandiritest.ui.destinations.CartScreenDestination
import id.renaldi.mandiritest.ui.destinations.HomeScreenDestination
import id.renaldi.mandiritest.ui.destinations.WishlistScreenDestination

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MandiriTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val navHostEngine = rememberNavHostEngine()
                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route

                    CustomScaffold(
                        navController = navController,
                        showBottomBar = route in listOf(
                            HomeScreenDestination.route,
                            WishlistScreenDestination.route,
                            CartScreenDestination.route,
                            AccountScreenDestination.route
                        )
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = navController,
                                engine = navHostEngine
                            )
                        }
                    }
                }
            }
        }
    }
}