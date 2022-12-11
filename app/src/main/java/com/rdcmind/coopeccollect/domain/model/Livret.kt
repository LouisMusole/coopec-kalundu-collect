package com.rdcmind.coopeccollect.domain.model

import java.util.Date

data class Livret(
    val docID : String = "",
    val collecteur : Collecteur? = null,
    val membre : Membre? = null,
    val dateCreation : Date? = null,
    val mandataire : String = "",
    val valeurMise : Int? = null,
    val zone : String = ""
)
