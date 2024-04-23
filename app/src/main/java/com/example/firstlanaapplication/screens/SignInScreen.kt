package com.example.firstlanaapplication.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.firstlanaapplication.R
import com.example.firstlanaapplication.components.ButtonComponent
import com.example.firstlanaapplication.components.NormalTextComponent
import com.example.firstlanaapplication.ui.theme.BgColor
import com.example.firstlanaapplication.ui.theme.Primary
import com.example.firstlanaapplication.viewmodel.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun SignInScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
    context: Context,
    navController: NavController
) {

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val maxChar = 18

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.login))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.username)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BgColor,
                    unfocusedContainerColor = BgColor,
                    disabledContainerColor = BgColor,
                    cursorColor = Primary,
                    focusedBorderColor = Primary,
                    focusedLabelColor = Primary,
                ),
                keyboardOptions = KeyboardOptions.Default,
                value = mainViewModel.loginText.value,
                maxLines = 1,
                onValueChange = {
                    if (it.length <= maxChar) {
                        mainViewModel.loginText.value = it.replace("\n", "")
                    }
                }
            )

            //Password field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.password)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BgColor,
                    unfocusedContainerColor = BgColor,
                    disabledContainerColor = BgColor,
                    cursorColor = Primary,
                    focusedBorderColor = Primary,
                    focusedLabelColor = Primary,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                value = mainViewModel.loginPassword.value,
                onValueChange = {
                    if (it.length <= maxChar) {
                        mainViewModel.loginPassword.value = it.replace("\n", "")
                    }
                },
                maxLines = 1,
                trailingIcon = {

                    val iconImage = if (passwordVisible.value) {
                        Icons.Filled.Close
                    } else {
                        Icons.Filled.Add
                    }

                    val description = if (passwordVisible.value) {
                        stringResource(id = R.string.hide)
                    } else {
                        stringResource(id = R.string.show)
                    }

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = iconImage, contentDescription = description)
                    }

                },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                value = stringResource(id = R.string.login)
            ) {
                mainViewModel.loginUser(context, navController)
            }
        }
    }
}