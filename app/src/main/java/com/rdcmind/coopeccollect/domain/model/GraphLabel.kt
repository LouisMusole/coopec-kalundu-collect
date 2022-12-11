package com.rdcmind.coopeccollect.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.rdcmind.coopeccollect.ui.theme.Bleu

data class GraphLabel(
    val pointsStart: Offset,
    val pointsEnd: Offset,
    val label: String,
    val color: Color = Bleu
)
