package com.rdcmind.coopeccollect.presentation.analyses

import android.graphics.PointF
import android.graphics.Typeface
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.rdcmind.coopeccollect.domain.model.GraphLabel
import com.rdcmind.coopeccollect.ui.theme.Bleu
import com.rdcmind.coopeccollect.ui.theme.Vert

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnalyseScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                .align(CenterHorizontally),
            textAlign = TextAlign.Center,
            text = "Analyse des activités réalisé dans l'axe dont vous êtes affecté",
            fontWeight = FontWeight.Bold
        )
        val pages = listOf(1, 2, 3)
        val pagerSate = rememberPagerState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White)
        ) {
            HorizontalPager(
                count = 3,
                state = pagerSate,
                itemSpacing = 16.dp,
                contentPadding = PaddingValues(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally

                ) {
                    Graphiques()
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 8.dp),
            pagerState = pagerSate
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(text = "Somme collectée par mise", fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "2500 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "5000 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "10 000 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "1500 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "20 000 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.2f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "100 000 CDF",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(text = "Top 5 collectes", fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "FURAHA CHAKUBUTA",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Dody Mwenemukono",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Wabala Buloze",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Blaise Mutambala",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Alain Cimanuka",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(text = "Top 5 retardateurs", fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Louis Musole",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Amani Bisimwa",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Grâce Dede",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Jonanthan Issa",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Yannick Nick",
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Bleu)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Graphiques(
    lineChartData: List<Int> = listOf(
        100, 400, 120, 432, 654, 342, 757, 295, 398, 901, 481, 825, 505, 243, 752, 23, 313, 435, 572,
        627, 736, 836, 925, 455, 645, 765, 123, 643, 785, 455, 757, 986, 333, 666, 88
    )
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Nombre collectes", color = Black)
        Row(verticalAlignment = CenterVertically) {
            Text(text = "123", fontSize = 30.sp)
            Icon(
                imageVector = Icons.Filled.ArrowCircleUp,
                contentDescription = null,
                tint = Vert
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            ){
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) ){
                val distance = size.width / lineChartData.size + 1
                var currentX = 0f
                val maxValue = lineChartData.maxOrNull() ?: 0
                val maxHeight = size.height
                val quotient = maxHeight/4
                val points = mutableListOf<PointF>()
                val lineLabelValues = listOf(
                    GraphLabel(
                        Offset(0f, maxHeight - (quotient*4)),
                        Offset(size.width, 0f),
                        maxValue.toString()
                    ),
                    GraphLabel(
                        Offset(0f, maxHeight - (quotient*3)),
                        Offset(size.width, maxHeight- (quotient*3)),
                        (maxValue-maxValue/3).toString()
                    ),
                    GraphLabel(
                        Offset(0f, maxHeight - (quotient*2)),
                        Offset(size.width, maxHeight - (quotient*2)),
                        (maxValue-maxValue/3).toString()
                ), GraphLabel(
                        Offset(0f, maxHeight - (quotient*1)),
                        Offset(size.width, maxHeight - (quotient*1)),
                        (maxValue/3).toString()
                    ),
                    GraphLabel(
                        Offset(0f, maxHeight - (quotient*0)),
                        Offset(size.width, maxHeight - (quotient*0)),
                        (maxValue/3).toString()
                    ))
                val textPaint = Paint().asFrameworkPaint().apply {
                    isAntiAlias= true
                    textSize = 8.sp.toPx()
                    color = android.graphics.Color.DKGRAY
                    typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
                }
                lineLabelValues.forEach { montant->
                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            montant.label,
                            montant.pointsStart.x,
                            montant.pointsStart.y - 5,
                            textPaint
                        )
                    }

                    drawLine(
                        color = montant.color,
                        start = montant.pointsStart,
                        end = montant.pointsEnd,
                        strokeWidth = 2f
                    )

                }

                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        "1/8.2022",
                        0f,
                        maxHeight + 25,
                        textPaint
                    )
                }

                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        "Aujourd'hui",
                        size.width - 150,
                        maxHeight + 25,
                        textPaint
                    )
                }


                lineChartData.forEachIndexed { index, data ->
                    if(lineChartData.size>=index+2){
                        val y0 = (maxValue-data)*(size.height/maxValue)
                        val x0=currentX+distance
                        points.add(PointF(x0, y0))
                        currentX+=distance
                        for (i in 0 until points.size-1){
                            drawLine(
                                start = Offset(points[i].x,points[i].y),
                                end = Offset(points[i+1].x,points[i+1].y),
                                color = Bleu,
                                strokeWidth = 3f

                            )
                        }
                    }
                }
            }
        }
    }
}