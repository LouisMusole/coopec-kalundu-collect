package com.rdcmind.coopeccollect.presentation.selectmembre

import com.rdcmind.coopeccollect.domain.model.Livret

data class SelectMembresState(
    val isSearchBarOpened :Boolean = false,
    val searchQuery : String = "",
    val loading : Boolean = false,
    val loadingJour : Boolean = true,
    val jourMois : String = "",
    val livrets : List<Livret> = arrayListOf(),
    val selectedLivret : Livret? = null,
    val saveStatus : String = ""
)
