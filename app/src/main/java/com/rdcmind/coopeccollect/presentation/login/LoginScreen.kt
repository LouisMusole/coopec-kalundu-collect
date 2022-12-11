package com.rdcmind.coopeccollect.presentation.login

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.rdcmind.coopeccollect.CollectMoneyActivity
import com.rdcmind.coopeccollect.MainActivity
import com.rdcmind.coopeccollect.ui.theme.Bleu
import com.rdcmind.coopeccollect.ui.theme.Purple700

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Contactez La COOPEC Kalundu en cas de problème"), onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp), style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Bleu
            )
        )

    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

         Text(text = "COOPEC KALUNDU", style = TextStyle(color = Bleu, fontSize = 30.sp, fontFamily = FontFamily.Monospace))

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = state.login,
            onValueChange = {
                            viewModel.onEvent(LoginEvent.OnLoginChange(it))
            }, label = { Text(text = "Login")}
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginEvent.OnPasswordChange(it))
            },
            label = { Text(text = "Mot de passe")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Bleu,
                contentColor = Color.White),
                onClick = {
                    viewModel.onEvent(LoginEvent.OnSubmitLogin(
                    state.login, state.password
                    ))

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "Connexion")
            }
        }
    }

    if (state.isLoading){
        Dialog(onDismissRequest = { /*TODO*/ }, DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ){
                CircularProgressIndicator(color = Bleu, modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    if(state.collecteur!=null){
        Intent(context, MainActivity::class.java).also {
            it.putExtra("collecteurID", state.collecteur!!.docID)
            it.putExtra("noms", state.collecteur!!.noms)
            context.startActivity(it)
            (context as? Activity)?.finish()
        }
    }
}