package com.rdcmind.coopeccollect.presentation.login

import com.rdcmind.coopeccollect.domain.model.Collecteur

data class LoginState(
    val collecteur : Collecteur? = null,
    val login : String = "",
    val password : String = "",
    val isLoading : Boolean = false
)


