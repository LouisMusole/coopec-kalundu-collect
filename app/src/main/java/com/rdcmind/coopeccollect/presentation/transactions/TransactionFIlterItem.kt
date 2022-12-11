package com.rdcmind.coopeccollect.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rdcmind.coopeccollect.ui.theme.Gris

@Composable
fun TransactionFilterItem(
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 4.dp)
            .clip(RoundedCornerShape(50))
            .background(Gris),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Plus rescentes",
            color = Color.Black,
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Color.DarkGray,
            )
        }
    }
}