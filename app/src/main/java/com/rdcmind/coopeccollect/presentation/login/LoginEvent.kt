package com.rdcmind.coopeccollect.presentation.login

sealed class LoginEvent{
    data class OnLoginChange(val loginText : String) : LoginEvent()
    data class OnPasswordChange(val passwordText : String) : LoginEvent()
    data class OnSubmitLogin(val login : String, val password : String) : LoginEvent()
}
