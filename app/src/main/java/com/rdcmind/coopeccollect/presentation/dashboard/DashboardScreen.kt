package com.rdcmind.coopeccollect.presentation.dashboard

import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rdcmind.coopeccollect.R
import com.rdcmind.coopeccollect.presentation.selectmembre.SelectMembresViewModel
import com.rdcmind.coopeccollect.ui.theme.Bleu
import com.rdcmind.coopeccollect.ui.theme.Vert


@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(50),
                ) {
                    Icon(
                        modifier = Modifier.size(38.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        tint = Color.DarkGray
                    )
                }

                Column() {
                    Text(text = (context as Activity).intent.getStringExtra("noms")!!, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Agent collecteur", fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Analyses", fontWeight = FontWeight.Bold)
                Text(text = "Resumé global", fontWeight = FontWeight.Light, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nbre collectés",
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        Text(text = "${state.nbreCollecte}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.ArrowCircleUp,
                            contentDescription = null,
                            tint = Vert,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Montant collecté",
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        Text(text = "${state.montantCollecte} CDF", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.ArrowCircleUp,
                            contentDescription = null,
                            tint = Vert,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Moyenne des collectes",
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    ) {
                        Text(text = "${state.moyenneCollecte} CDF", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Filled.ArrowCircleUp,
                            contentDescription = null,
                            tint = Vert,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "VOIR PLUS",
                    color = Bleu,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate("analyses")
                    }
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Transactions", fontWeight = FontWeight.Bold)
                Text(text = "3 derniers jours", fontWeight = FontWeight.Light, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                state.cotisations.take(5).forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier.weight(2f),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Surface(
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                shape = RoundedCornerShape(50),
                            ) {
                                Icon(
                                    modifier = Modifier.size(38.dp),
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = null,
                                    tint = Color.DarkGray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(text = "${it.livret!!.membre!!.noms} - Zone ${it.livret.zone}", fontSize = 14.sp, color = Color.DarkGray)
                                Text(text = "${it.montant} CDF", fontSize = 16.sp)
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Icon(imageVector = Icons.Filled.Lens, contentDescription = null, modifier = Modifier.size(15.dp), tint = Color.DarkGray)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "VOIR PLUS",
                    color = Bleu,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate("transactions")
                    }
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Membres", fontWeight = FontWeight.Bold)
                Text(text = "${state.membres.size} membres", fontWeight = FontWeight.Light, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                state.membres.take(5).forEach {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(2f)) {
                            Icon(
                                modifier = Modifier.size(38.dp),
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "${it.membre?.noms}", fontSize = 14.sp)

                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = "${it.zone}", fontSize = 12.sp)
                        }

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "VOIR PLUS",
                    color = Bleu,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate("membres")
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


