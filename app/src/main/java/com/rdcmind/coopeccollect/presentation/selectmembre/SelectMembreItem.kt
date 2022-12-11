package com.rdcmind.coopeccollect.presentation.selectmembre

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rdcmind.coopeccollect.domain.model.Livret
import com.rdcmind.coopeccollect.ui.theme.Bleu

@Composable
fun SelectMembreItem(
    livret : Livret,
    modifier : Modifier = Modifier
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
                .size(50.dp)
                .clip(RoundedCornerShape(50))
                .background(Bleu),
            contentAlignment = Alignment.Center
        ){
            Text(text = livret.membre!!.noms[0].toString())
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = livret.membre!!.noms,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "${livret.valeurMise} CDF",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}
