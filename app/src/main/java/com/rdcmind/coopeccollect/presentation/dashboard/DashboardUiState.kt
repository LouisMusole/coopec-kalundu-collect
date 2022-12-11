package com.rdcmind.coopeccollect.presentation.dashboard

import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.model.Livret

data class DashboardUiState(
    val loading : Boolean = true,
    val loadingMembre : Boolean= true,
    val nbreCollecte : Int = 0,
    val montantCollecte : Int = 0,
    val moyenneCollecte : Int = 0,
    val cotisations : List<Cotisation> = arrayListOf(),
    val membres : List<Livret> = arrayListOf()
)
