package com.rdcmind.coopeccollect.presentation.selectmembre

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rdcmind.coopeccollect.MainActivity
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.ui.theme.Bleu
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectMembreScreen(
    navController: NavHostController,
    viewModel: SelectMembresViewModel = hiltViewModel()
) {

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Révision de la collecte",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        OutlinedButton(
                            onClick = {
                                scope.launch {
                                    bottomSheetState.hide()
                                }
                            },
                            shape = RoundedCornerShape(50),

                            ) {

                            Text(text = "Fermer", color = Color.Black)

                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Noms",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    state.selectedLivret?.membre?.let {
                        Text(
                            text = it.noms,
                            fontWeight = FontWeight.Light,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Mise",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${state.selectedLivret?.valeurMise} CDF",
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Pour",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    if (state.loadingJour) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .size(35.dp)
                        ) {
                            CircularProgressIndicator(
                                color = Bleu
                            )
                        }
                    }
                    if (!state.loadingJour) {
                        Text(
                            text = state.jourMois,
                            fontWeight = FontWeight.Light,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                    }

                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "En validant cette opération vous êtes sûr de cette action et vous etes engagés.",
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {
                        if (state.selectedLivret == null || state.jourMois == "") {
                            Toast.makeText(
                                context,
                                "Impossible d'enregistrer la collecte, toutes les ressources ne sont pas disponnibles",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            viewModel.addCotisation(
                                Cotisation(
                                    "",
                                    (context as Activity).intent.getStringExtra("collecteurID"),
                                    state.selectedLivret,
                                    state.jourMois.substringAfterLast("-").trim(),
                                    state.jourMois.substringBeforeLast("e").trim().toInt(),
                                    state.selectedLivret!!.valeurMise
                                )
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Bleu
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Confirmer collecte",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        },
        sheetShape = RoundedCornerShape(16.dp),

        ) {
        Scaffold(
            topBar = {
                MainAppBar(
                    context,
                    isSearchBarOpened = state.isSearchBarOpened,
                    searchTextState = state.searchQuery,
                    onTextChange = {
                        viewModel.OnEvent(SelectMembresEvent.OnUpdateSearchTextState(it))
                    },
                    onCloseClicked = {
                        viewModel.OnEvent(
                            SelectMembresEvent.OnUpdateWidgetState(
                                false
                            )
                        )
                    },
                    onSearchClicked = {
                        viewModel.OnEvent(SelectMembresEvent.OnUpdateWidgetState(true))
                        Log.d("MYAPP", "Clicked")
                    },
                    onSearchTriggered = {
                        viewModel.OnEvent(
                            SelectMembresEvent.OnUpdateWidgetState(
                                true
                            )
                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.livrets.size) { i ->
                        val livret = state.livrets[i]
                        SelectMembreItem(
                            livret,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .clickable {
                                    scope.launch {
                                        viewModel.OnEvent(SelectMembresEvent.OnSelectedLivret(livret))
                                        bottomSheetState.show()
                                    }
                                }
                                .padding(8.dp)
                        )
                        if (i < state.livrets.size) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }


    }

    if (state.saveStatus == "Loading") {
        Dialog(
            onDismissRequest = { /*TODO*/ },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator(color = Bleu, modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    if (state.saveStatus == "Loading Off") {
        (context as? Activity)?.finish()
        /*Intent(context, MainActivity::class.java).also {
            context.startActivity(it)
        }*/
    }

}

@Composable
fun MainAppBar(
    context: Context,
    isSearchBarOpened: Boolean,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    if (!isSearchBarOpened) {
        NormalAppBar(context, onSearchTriggered)
    } else {
        SearchAppBar(
            text = searchTextState,
            onTextChange = onTextChange,
            onCloseClicked = onCloseClicked, onSearchClicked = onSearchClicked
        )
    }
}

@Composable
fun NormalAppBar(context: Context, onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Sélectionner membre",
                color = Color.Black,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "AAA", Toast.LENGTH_SHORT).show()
                }
            )
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.DarkGray,
                )
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Color.DarkGray,
                modifier = Modifier.clickable {
                    val activity = (context as? Activity)
                    activity?.finish()
                }
            )
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Rechercher",
                    modifier = Modifier.alpha(ContentAlpha.medium),
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = Color.Black
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.alpha(ContentAlpha.medium)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.DarkGray
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Search Icon",
                        tint = Color.DarkGray
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }


}