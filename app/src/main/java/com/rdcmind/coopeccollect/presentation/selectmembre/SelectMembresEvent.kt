package com.rdcmind.coopeccollect.presentation.selectmembre

import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.model.Livret

sealed class SelectMembresEvent {
    data class OnUpdateWidgetState(val newValue : Boolean) : SelectMembresEvent()
    data class OnUpdateSearchTextState(val newValue : String) : SelectMembresEvent()
    data class OnSelectedLivret(val livret: Livret) : SelectMembresEvent()
    data class OnSaveCotisation(val cotisation: Cotisation) : SelectMembresEvent()
}