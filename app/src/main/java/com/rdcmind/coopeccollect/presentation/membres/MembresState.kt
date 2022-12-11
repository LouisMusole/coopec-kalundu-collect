package com.rdcmind.coopeccollect.presentation.membres

import com.rdcmind.coopeccollect.domain.model.Livret

data class MembresState(
    val membres : List<Livret> = emptyList(),
    val loading : Boolean = true
)
