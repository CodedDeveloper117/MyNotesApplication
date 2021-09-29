package com.codedev.mynotesapplication.presentation.add_edit_notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.codedev.mynotesapplication.ui.theme.CustomLightDarkGray
import com.codedev.mynotesapplication.ui.theme.TextWhite

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    textStyle: TextStyle,
    hint: String = "",
    singleLine: Boolean,
    onTextChange: (String) -> Unit,
    value: String
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = {
                onTextChange(it)
            },
            singleLine = singleLine,
            modifier = Modifier.fillMaxWidth(),
            textStyle = textStyle.copy(color = CustomLightDarkGray)
        )
        if(isHintVisible) Text(text = hint, color = TextWhite, style = textStyle)

    }
}