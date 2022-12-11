package com.rdcmind.coopeccollect.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rdcmind.coopeccollect.presentation.membres.MembreItem
import kotlinx.coroutines.launch

@Composable
fun TransactionsScreen(
    navController: NavHostController,
    viewModel: TransactionsViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(state.cotisations.size){i->
                val cotisation=state.cotisations[i]
                TransactionItem(
                    cotisation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            /*TODO*/
                        }
                        .padding(8.dp)
                )
                if(i<state.cotisations.size){
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}