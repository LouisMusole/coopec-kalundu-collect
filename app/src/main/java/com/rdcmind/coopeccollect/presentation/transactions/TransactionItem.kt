package com.rdcmind.coopeccollect.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.ui.theme.Bleu
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionItem(
    cotisation: Cotisation,
    modifier: Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(height = 50.dp, width = 8.dp)
                .clip(RoundedCornerShape(50))
                .background(Bleu),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Text(
                text = "${cotisation.livret?.membre?.noms}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "${cotisation.mois}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${cotisation.montant} CDF",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(cotisation.dateCotisation!!),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}