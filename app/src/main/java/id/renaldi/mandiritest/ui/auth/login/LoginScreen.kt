package id.renaldi.mandiritest.ui.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oyamo.dooka_app.core.util.UiEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.renaldi.mandiritest.core.presentation.theme.Purple40
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val usernameState = viewModel.usernameState.value
    val passwordState = viewModel.passwordState.value
    val rememberMeState = viewModel.rememberMeState.value

    val loginState = viewModel.loginState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is UiEvents.NavigateEvent -> {
                    navigator.navigate(
                        event.route
                    )
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Login successful",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column(
                Modifier.padding(16.dp), verticalArrangement = Arrangement.Top
            ) {
                Text(text = "Welcome Back", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Login to your account to continue shopping",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = usernameState.text,
                    onValueChange = {
                        viewModel.setUsername(it)
                    },
                    label = {
                        Text(text = "Username")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    isError = usernameState.error != null

                )
                if (usernameState.error != "") {
                    Text(
                        text = usernameState.error ?: "",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordState.text,
                    onValueChange = {
                        viewModel.setPassword(it)
                    },
                    label = {
                        Text(text = "Password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                    ),
                    isError = passwordState.error != null

                )
                if (passwordState.error != "") {
                    Text(
                        text = passwordState.error ?: "",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = rememberMeState, onCheckedChange = {
                        viewModel.setRememberMe(it)
                    })
                    Text(text = "Remember me", fontSize = 12.sp)
                }
                TextButton(onClick = {
                }) {
                    Text(text = "Forgot password?")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.loginUser()
                },
                shape = RoundedCornerShape(8)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp), text = "Sign In", textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account?")
                        append(" ")
                        withStyle(
                            style = SpanStyle(color = Purple40, fontWeight = FontWeight.Bold)
                        ) {
                            append("Sign Up")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }

            if (loginState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }
        }
    }
}