package com.rdcmind.coopeccollect.presentation.selectmembre

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.util.Ressource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectMembresViewModel @Inject constructor(
    private val repository: CoopecRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SelectMembresState())
    val state : StateFlow<SelectMembresState> = _state.asStateFlow()

    init {
        getLivrets("1vktBx5JTSENxb2BDIyl")
    }

    fun OnEvent(event: SelectMembresEvent){
        when(event){
            is SelectMembresEvent.OnUpdateWidgetState->{
                _state.update { currentState->
                    currentState.copy(
                        isSearchBarOpened = !_state.value.isSearchBarOpened
                    )
                }
            }
            is SelectMembresEvent.OnUpdateSearchTextState->{
                _state.update { currentState->
                    currentState.copy(
                        searchQuery = event.newValue
                    )
                }
            }

            is SelectMembresEvent.OnSelectedLivret->{

                getJourCotisation(event.livret.membre!!.docID)
                _state.update { currentState->
                    currentState.copy(selectedLivret = event.livret)
                }
            }

            is SelectMembresEvent.OnSaveCotisation->{
                addCotisation(event.cotisation)
            }
        }
    }
    fun getLivrets(collecteur : String){
        viewModelScope.launch {
            repository.getLivrets(collecteur)
                .collect{result->
                    when(result){
                        is Ressource.Success -> {
                            result.data?.let { livrets->
                                _state.update { currentState->
                                    currentState.copy(
                                        livrets =livrets
                                    )
                                }
                            }
                            Log.i("COOPECAPP", result.data.toString())
                        }
                        is Ressource.Error ->{
                            Log.e("COOPECAPP", result.message!!)
                        }
                        is Ressource.Loading ->{
                            _state.update { currentState->
                                currentState.copy(loading = result.isLoading)
                            }
                        }
                    }
                }
        }
    }
    fun getJourCotisation(membre : String){
        viewModelScope.launch {
            repository.getJourCotisaion(membre)
                .collect{result->
                    when(result){
                        is Ressource.Success->{
                            _state.update { currentState->
                                currentState.copy(jourMois = result.data!!)
                            }
                            Log.d("COOPECAPPP", result.data!!)

                        }
                        is Ressource.Error->{
                            Log.e("COOPECAPP", result.message!!)
                        }
                        is Ressource.Loading->{
                            _state.update { currentState->
                                currentState.copy(loadingJour = result.isLoading)
                            }
                        }
                    }
                    Log.d("COOPECAPP", result.toString())

                }
        }
    }

    fun addCotisation(cotisation: Cotisation){
        viewModelScope.launch {
            repository.addCotisation(cotisation).collect{result->
                when(result){
                    is Ressource.Success->{
                        _state.update { currentState->
                            currentState.copy(saveStatus = result.data!!)
                        }
                    }
                    is Ressource.Error->{
                        _state.update { currentState->
                            currentState.copy(saveStatus = result.message!!)
                        }
                        Log.e("COOPECAPP", result.message!!)
                    }
                    is Ressource.Loading->{
                        _state.update { currentState->
                            currentState.copy(saveStatus = if(result.isLoading) "Loading" else  "Loading Off")
                        }
                    }
                }
            }
        }
    }
}

