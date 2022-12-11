package com.rdcmind.coopeccollect.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdcmind.coopeccollect.domain.model.Collecteur
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.util.Ressource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: CoopecRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state : StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event : LoginEvent){
        when (event) {
            is LoginEvent.OnSubmitLogin ->{
                login()
            }
            is LoginEvent.OnLoginChange ->{
                _state.update {currentState->
                    currentState.copy(login = event.loginText)
                }
            }
            is LoginEvent.OnPasswordChange ->{
                _state.update { currentState->
                    currentState.copy(password = event.passwordText)

                }
            }
        }
    }

    fun login(){
        viewModelScope.launch {
            repository.login(_state.value.login, _state.value.password)
                .collect{ result->
                    when (result){
                        is Ressource.Success ->{
                            result.let {
                                _state.update { currentState->
                                    currentState.copy(collecteur = it.data)
                                }
                                Log.i("COOPECAPP",it.data.toString())
                            }
                        }
                        is Ressource.Loading ->{
                            result.let {
                                _state.update { currentState->
                                    currentState.copy(isLoading = it.isLoading)
                                }
                            }
                        }
                        is Ressource.Error ->{
                            result.message?.let {
                                Log.i("COOPECAPP", it)
                            }
                        }
                    }


                }
        }
    }
}

