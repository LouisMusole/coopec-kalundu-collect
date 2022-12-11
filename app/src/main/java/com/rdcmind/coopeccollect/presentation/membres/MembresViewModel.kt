package com.rdcmind.coopeccollect.presentation.membres

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.util.Ressource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembresViewModel @Inject constructor(
    private val repository: CoopecRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MembresState())
    val state : StateFlow<MembresState> = _state.asStateFlow()

    init {
        getLivrets("1vktBx5JTSENxb2BDIyl")
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
                                currentState.copy(loading = result.isLoading)
                            }
                        }
                    }
                }
        }
    }
}