package com.rdcmind.coopeccollect.presentation.transactions

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.presentation.dashboard.DashboardUiState
import com.rdcmind.coopeccollect.util.Ressource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val repository: CoopecRepository
) : ViewModel() {
    var _state = MutableStateFlow(TransactionsState())
    val state : StateFlow<TransactionsState> = _state.asStateFlow()

    init {
        getCotisations("1vktBx5JTSENxb2BDIyl")
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
                                        cotisations = it!!
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
}