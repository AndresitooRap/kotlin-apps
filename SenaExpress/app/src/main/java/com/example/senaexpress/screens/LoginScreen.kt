package com.example.senaexpress.screens

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.senaexpress.R
import com.example.senaexpress.components.CreateNotificationChannel
import com.example.senaexpress.components.Screens
import com.example.senaexpress.components.SimpleNotification
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.launch

//Funcion para poder poner el video
private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL //El video se repite indefinidamente
        playWhenReady = true //El video inicia cuando se abre la app
        prepare()
    }

//Funcion para poner los parametros del video al mostrar
private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false //Se pone en false para al presionar el video no se muestre el botón de "play" y demás
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }

@Composable
fun LoginScreen(videoUri: Uri, navController: NavHostController) {
    //Contexto de lo que esta en el momento
    val context = LocalContext.current
    //Recuerda el video
    val exoPlayer = remember { context.buildExoPlayer(videoUri) }
    //Recuerda el estado de el modalbottom
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

    //Efecto para el video
    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    ModelBottomSheet(showModalBottomSheet, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelBottomSheet(
    showModalBottomSheet: MutableState<Boolean>,
    navController: NavHostController,
) {
    //Valor para poder cambiar el estado
    val showLoginForm = rememberSaveable { mutableStateOf(true) }
    //Efecto al abrir el modalbottom
    val scope = rememberCoroutineScope()
    //Estado al saber si esta abierto o no el modalbottom
    val skipPartially by remember { mutableStateOf(false) }
    //Valor para saber el estado sobre el bottomsheet
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    //Valor para saber hasta donde llegara el modal
    val expandAnimation = animateFloatAsState(
        targetValue = if (bottomSheetState.isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    //Notification
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    LaunchedEffect(Unit) {
        CreateNotificationChannel(
            idChannel,
            context,
            name,
            descriptionText
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsWithImePadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){}
        //Valor para la rotación del icono
        val iconRotation by animateFloatAsState(
            targetValue = if (expandAnimation.value == 1f) 180f else 0f,
            animationSpec = tween(durationMillis = 300)
        )

        //El box es una caja para poder clickearla y poder abrir el modal y cerrarlo
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(60.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .clickable {
                    scope.launch {
                        if (bottomSheetState.isVisible) {
                            bottomSheetState.hide()
                        } else {
                            showModalBottomSheet.value = true
                            bottomSheetState.show()
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Row() {
                Text(stringResource(id = R.string.Next_to))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(iconRotation)
                )
            }

        }
    }

    if (showModalBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            tonalElevation = 1.dp,
            containerColor = MaterialTheme.colorScheme.tertiary,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (showLoginForm.value) {
                        Text(
                            stringResource(id = R.string.Sign_in),
                            style = TextStyle(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    } else {
                        Text(
                            stringResource(id = R.string.Sign_up), style = TextStyle(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
                Image(
                    painter = painterResource(R.drawable.logo_sena),
                    contentDescription = "Logo sena",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 5.dp)
                )
                if (showLoginForm.value) {
                    UserForm(
                        isCreateAccount = false,
                        onDone = { email, password ->
                            //Log.d("TiendaApp", "Inicio sesión con $email y $password")
                            navController.navigate(Screens.HomeScreen.route) {
                                popUpTo(Screens.LoginScreen.route) { inclusive = true }
                            }
                            SimpleNotification(
                                context,
                                idChannel,
                                idNotification,
                                "Sign in",
                                "You sign in with $email y $password"
                            )

                        }
                    )
                } else {
                    UserForm(
                        isCreateAccount = true,
                        onDone = { email, password ->
                            //Log.d("TiendaApp", "Creando cuenta con $email y $password")
                            navController.navigate(Screens.HomeScreen.route) {
                                popUpTo(Screens.LoginScreen.route) { inclusive = true }
                            }
                            SimpleNotification(
                                context,
                                idChannel,
                                idNotification,
                                "Sign up",
                                "You sign up with $email y $password"
                            )
                        }
                    )

                }
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 20.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    val text1 =
                        if (showLoginForm.value) {
                            stringResource(id = R.string.Dont_have_account)
                        } else {
                            stringResource(id = R.string.You_ve_account)
                        }
                    val text2 =
                        if (showLoginForm.value) {
                            stringResource(id = R.string.Register_Free)
                        } else {
                            stringResource(id = R.string.Log_In)
                        }
                    Text(text1, color = MaterialTheme.colorScheme.onPrimary)
                    Text(
                        text2,
                        modifier = Modifier
                            .clickable { showLoginForm.value = !showLoginForm.value }
                            .padding(start = 5.dp),
                        color = Color((0xFF2FAA16))
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = { email, pwd -> }
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    val validState = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )

    }
    Row() {
        SubmitButton(
            textId = if (isCreateAccount)
                stringResource(id = R.string.Create_Account)
            else
                stringResource(id = R.string.Log_In),
            inputValid = validState
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValid: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2FAA16),
        ),
        enabled = inputValid,
    ) {
        Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = {
            Text(
                text = labelId
            )
        },
        shape = CircleShape,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF2FAA16),
            focusedLabelColor = Color(0xFF2FAA16),
        ),
        prefix = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()) PasswordVisibleIcon(
                passwordVisible
            ) else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image = if (passwordVisible.value)
        Icons.Default.VisibilityOff
    else
        Icons.Default.Visibility
    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        prefix = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        singleLine = isSingleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF2FAA16),
            focusedLabelColor = Color(0xFF2FAA16),
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = CircleShape
    )
}
