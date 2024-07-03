package com.example.todolist.presentation.view.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowErrorPopup(errorMessage: String,navigateBack: () -> Unit) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = "Error") },
        text = { Text(text = errorMessage) },
        confirmButton = {
            Button(
                onClick = { navigateBack() }
            ) {
                Text("OK")
            }

        }
    )
}
