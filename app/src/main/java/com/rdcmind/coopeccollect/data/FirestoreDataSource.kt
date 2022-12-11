package com.rdcmind.coopeccollect.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.rdcmind.coopeccollect.domain.model.Collecteur
import com.rdcmind.coopeccollect.domain.model.Cotisation
import com.rdcmind.coopeccollect.domain.model.Livret
import com.rdcmind.coopeccollect.domain.model.Mois
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*


object FirestoreDataSource {
    suspend fun loginCollecteur(login : String, password : String) : Collecteur ?{
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("collecteurs")
                .whereEqualTo("login",login)
                .whereEqualTo("password",password)
                .get().await().documents[0].toObject(Collecteur::class.java)
        }catch (e: Exception){
            Log.e("COOPECAPP", "Erreur", e)
            null
        }
    }

    suspend fun getLivrets(collecteur : String) : List<Livret>? {
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("livrets")
                .whereEqualTo("collecteur.docID", collecteur)
                .get()
                .await().toObjects(Livret::class.java)
        }catch (e : Exception){
            Log.e("COOPECAPP", "Erreur", e)
            null
        }
    }

    suspend fun getJourCotisation(membre : String) : String?{
        val db = FirebaseFirestore.getInstance()
        return try {

            val moisActif = db.collection("mois")
                .whereEqualTo("isActif", true)
                .get().await().documents[0].toObject(Mois::class.java)

            val jour = db.collection("cotisations")
                .whereEqualTo("livret.membre.docID", membre)
                .whereEqualTo("mois", moisActif?.designation)
                .get().await().documents.size + 1

            Log.e("COOPECAPPP", jour.toString())


            if(jour>31){
                "Déjà atteint la limite"
            }else{
                "$jour${if(jour==1) "er" else "e"} jour - ${moisActif!!.designation} "
            }
        }catch (e : Exception){
            Log.e("COOPECAPP", "Erreur", e)
            null
        }
    }

    suspend fun addCotisation(cotisation:Cotisation) : String{
        val db = FirebaseFirestore.getInstance()
        return try {
            if(cotisation.jour!! >=31){
                "Error"
            }else{
                db.collection("cotisations")
                    .add(cotisation)
                    .await()
                "Saved"
            }
        }catch (e : Exception){
            Log.e("COOPECAPP", "Erreur", e)
            "Error"
        }
    }


    suspend fun getCotisations(collecteur: String) : List<Cotisation>?{
        val db =FirebaseFirestore.getInstance()
        return try {
            db.collection("cotisations")
                .whereEqualTo("collecteurId", collecteur)
                .orderBy("dateCotisation", Query.Direction.DESCENDING)
                .get()
                .await()
                .toObjects(Cotisation::class.java)
        }catch (e : Exception){
            Log.e("COOPECAPP", "Erreur", e)
            null
        }
    }



}