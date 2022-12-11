package com.rdcmind.coopeccollect.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.rdcmind.coopeccollect.R

sealed class CoopecScreen(val route: String, @StringRes val resourceId: Int?) {
    object Dashboard : CoopecScreen("dashboard", R.string.dashboard)
    object Transactions : CoopecScreen("transactions", R.string.transactions)
    //object Analyses : CoopecScreen("analyses", R.string.analyses)
    object Membres : CoopecScreen("membres", R.string.membres)
    object SelectMembres : CoopecScreen("select_membres", null )
}