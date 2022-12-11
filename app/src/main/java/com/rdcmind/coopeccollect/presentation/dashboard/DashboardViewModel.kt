package com.rdcmind.coopeccollect.presentation.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.util.Ressource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: CoopecRepository
) : ViewModel()  {
    private val _state = MutableStateFlow(DashboardUiState())
    val state : StateFlow<DashboardUiState> = _state.asStateFlow()

    init {
        getCotisations("1vktBx5JTSENxb2BDIyl")
        getLivrets("1vktBx5JTSENxb2BDIyl")
    }

    fun getCotisations(collecteur : String){
        viewModelScope.launch {
            repository.getCotisations(collecteur)
                .collect{result->
                    when(result){
                        is Ressource.Success->{
                            _state.update { currentState->
                                result.data.let {
                                    currentState.copy(
                                        nbreCollecte = it!!.size,
                                        montantCollecte = it.sumOf {c-> c.montant!! },
                                        moyenneCollecte = if(it.isEmpty()) 0 else it.sumOf { c-> c.montant!! }/it.size,
                                        cotisations = it
                                    )
                                }
                            }
                        }
                        is Ressource.Error->{
                            Log.e("COOPECAPP", result.message!!)
                        }
                        is Ressource.Loading->{
                            _state.update { currentState->
                                currentState.copy(loading = result.isLoading)
                            }
                        }
                    }
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
                                        membres =livrets
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
                                currentState.copy(loadingMembre = result.isLoading)
                            }
                        }
                    }
                }
        }
    }

}