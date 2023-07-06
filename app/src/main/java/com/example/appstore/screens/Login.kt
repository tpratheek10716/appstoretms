package com.example.appstore.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appstore.App
import com.example.appstore.Routes
import com.example.appstore.data.LoginRequestData
import com.example.appstore.utils.getLoginReqData
import com.example.appstore.viewmodel.LoginViewModel

@Composable
fun LoginPage(navController: NavController,loginViewModel: LoginViewModel) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        val successState by loginViewModel.successLoginData().observeAsState()
        val errorState by loginViewModel.errorLoginData().observeAsState()
        val networkState by loginViewModel.networkFailureUIData().observeAsState()
        val loginState by loginViewModel.loginState.collectAsState()

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (!username.value.equals("") && !password.value.equals("")) {
                        //perform login
                        val loginRequestData = getLoginReqData()

                        loginViewModel.doLogin(loginRequestData)

                        //states
                        Log.e("State success", "$successState")
                        Log.e("State error", "$errorState")
                        Log.e("State network fail", "$networkState")
                        Log.e("Login state", "$loginState")

                        /*if(successState != null)
                        navController.navigate(Routes.AppList.route)
                    else if (networkState!= null)
                        Toast.makeText(
                            context,
                            "Network failure",
                            Toast.LENGTH_SHORT
                        ).show()
                    else if(errorState!=null)
                        Toast.makeText(
                            context,
                            "Error logging on",
                            Toast.LENGTH_SHORT
                        ).show()*/

                        //remove when api up
                        navController.navigate(Routes.AppList.route)
                    }
                    else
                        Toast.makeText(
                            context,
                            "Username password required",
                            Toast.LENGTH_SHORT
                        ).show()

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
}
