package com.rdcmind.coopeccollect.data

import com.rdcmind.coopeccollect.domain.model.Collecteur
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.model.Livret
import com.rdcmind.coopeccollect.domain.repository.CoopecRepository
import com.rdcmind.coopeccollect.util.Ressource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoopecRepsitoryImpl @Inject constructor() : CoopecRepository {
    override suspend fun login(
        login: String, password: String
    ): Flow<Ressource<Collecteur>> {
        return flow {
            emit(Ressource.Loading(true))

            val collecteur = FirestoreDataSource.loginCollecteur(login, password)

            emit(Ressource.Success(collecteur))

            emit(Ressource.Loading(false))
        }
    }

    override suspend fun getLivrets(collecteur: String): Flow<Ressource<List<Livret>>> {
        return flow {
            emit(Ressource.Loading(true))
            val livrets = FirestoreDataSource.getLivrets(collecteur)
            if (livrets != null) {
                emit(Ressource.Success(livrets))
            }else{
                emit(Ressource.Error("Vide"))
            }
            emit(Ressource.Loading(false))
        }
    }

    override suspend fun getJourCotisaion(membre: String): Flow<Ressource<String>> {
        return flow {
            emit(Ressource.Loading(true))

            val jour = FirestoreDataSource.getJourCotisation(membre)
            if (jour != null) {
                if(jour=="Déjà atteint la limite"){
                    emit(Ressource.Error("Finished"))
                }else{
                    emit(Ressource.Success(jour))
                }
            }
            emit(Ressource.Loading(false))
        }
    }

    override suspend fun addCotisation(cotisation: Cotisation): Flow<Ressource<String>> {
        return flow {
            emit(Ressource.Loading(true))
            val msg = FirestoreDataSource.addCotisation(cotisation)
            if (msg =="Saved"){
                emit(Ressource.Success(msg))
            }else{
                emit(Ressource.Error(msg))
            }
            emit(Ressource.Loading(false))
        }
    }

    override suspend fun getCotisations(collecteur: String): Flow<Ressource<List<Cotisation>>> {
        return flow {
            emit(Ressource.Loading(true))
            val cotisations = FirestoreDataSource.getCotisations(collecteur)
            if(cotisations==null){
                emit(Ressource.Error("Erreur"))
            }else{
                emit(Ressource.Success(cotisations))
            }
            emit(Ressource.Loading(false))
        }
    }
}