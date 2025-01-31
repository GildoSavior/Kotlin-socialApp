package com.example.mysocialapp.android.common.components

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.mysocialapp.android.common.theming.Typography
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysocialapp.android.R
import com.example.mysocialapp.android.common.theming.Gray
import com.example.mysocialapp.android.common.theming.SocialAppTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordField: Boolean = false,
    isSingleLineField: Boolean = true,
    @StringRes hint: Int
) {
    val isDarkTheme = isSystemInDarkTheme()

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = Typography.body2,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        singleLine = isSingleLineField,
        colors = TextFieldDefaults.colors(
            if (isDarkTheme) MaterialTheme.colors.surface else Gray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = if (isPasswordField) {
            {
                PasswordEyeIcon(isPasswordVisible = isPasswordVisible) {
                    isPasswordVisible = !isPasswordVisible
                }
            }
        } else {
            null
        },
        visualTransformation = if (isPasswordField) {
            if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        placeholder =  {
            Text(text = stringResource(id = hint), style = Typography.body2)
        },
        shape = MaterialTheme.shapes.medium
    )
}

@Composable
fun PasswordEyeIcon(
    isPasswordVisible : Boolean = true,
    onPasswordVisibilityToggle: () -> Unit
) {
    var image = if (isPasswordVisible) {
        painterResource(id = R.drawable.show_eye_icon_filled)
    } else {
      painterResource(id = R.drawable.hide_eye_icon_filled)
    }

    Button(
        onClick = { onPasswordVisibilityToggle() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            painter = image,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Preview
@Composable
fun CustomTextFieldPreview() {
    SocialAppTheme {
        CustomTextField(value = "", onValueChange ={} , hint = androidx.compose.ui.R.string.default_error_message )
    }
}

