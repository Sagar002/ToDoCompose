package com.example.todolist.presentation.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemUI(text: String) {
    Card(
        modifier = Modifier.padding(top = 6.dp, bottom = 3.dp)
            .wrapContentSize(align = Alignment.Center),
        shape = RoundedCornerShape(corner = CornerSize(size = 0.dp))

    ) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
        )
    }

}