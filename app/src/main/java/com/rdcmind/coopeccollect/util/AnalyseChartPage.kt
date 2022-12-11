package com.rdcmind.coopeccollect.util

sealed class AnalyseChartPage<T>(
    val data : T? = null,
    val titre : String,
){
    class NombreCollectes<T>(data : T?) : AnalyseChartPage<T>(data, "Membres colletés")
    class MontantCollectes<T>(data : T?) : AnalyseChartPage<T>(data, "Argent colleté")
    class MoyenneCollectes<T>(data : T?) : AnalyseChartPage<T>(data, "Moyenne de l'argent colleté")
}
