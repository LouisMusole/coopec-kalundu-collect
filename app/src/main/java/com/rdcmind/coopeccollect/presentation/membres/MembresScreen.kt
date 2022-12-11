package com.rdcmind.coopeccollect.presentation.membres

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import kotlinx.coroutines.launch

@Composable
fun MembresScreen(navController: NavHostController,
                  viewModel: MembresViewModel= hiltViewModel()
                  ){
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(state.membres.size){i->
                val membre=state.membres[i]
                MembreItem(
                    membre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            /* */
                        }
                        .padding(8.dp)
                )
                if(i<state.membres.size){
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}