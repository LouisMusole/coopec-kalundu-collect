package com.rdcmind.coopeccollect.presentation.transactions

import com.rdcmind.coopeccollect.domain.model.Cotisation

data class TransactionsState(
    val loading: Boolean = true,
    val cotisations : List<Cotisation> = emptyList()
)