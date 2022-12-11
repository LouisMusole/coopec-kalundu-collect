package com.rdcmind.coopeccollect.domain.repository

import com.rdcmind.coopeccollect.domain.model.Collecteur
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.model.Livret
import com.rdcmind.coopeccollect.util.Ressource
import kotlinx.coroutines.flow.Flow

interface CoopecRepository {
    suspend fun  login(
        login : String, password : String
    ) : Flow<Ressource<Collecteur>>

    suspend fun getLivrets(
        collecteur: String
    ):Flow<Ressource<List<Livret>>>

    suspend fun getJourCotisaion(
        membre : String
    ) : Flow<Ressource<String>>

    suspend fun addCotisation(
        cotisation: Cotisation
    ): Flow<Ressource<String>>

    suspend fun getCotisations(
        collecteur: String
    ) : Flow<Ressource<List<Cotisation>>>
}