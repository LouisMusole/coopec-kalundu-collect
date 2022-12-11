package com.rdcmind.coopeccollect.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Cotisation(
    val docId: String = "",
    val collecteurId : String? = "",
    val livret: Livret?= null,
    val mois: String = "",
    val jour: Int? = null,
    val montant: Int? = null,
    @ServerTimestamp
    val dateCotisation: Date? = null
)
