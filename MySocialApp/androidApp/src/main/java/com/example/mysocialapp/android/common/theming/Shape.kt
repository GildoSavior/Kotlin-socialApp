package com.example.mysocialapp.android.common.theming

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import com.example.mysocialapp.android.common.theming.LargeSpacing
import com.example.mysocialapp.android.common.theming.MediumSpacing
import com.example.mysocialapp.android.common.theming.SmallSpacing

val Shapes = Shapes(
    small = RoundedCornerShape(SmallSpacing),
    medium = RoundedCornerShape(MediumSpacing),
    large = RoundedCornerShape(LargeSpacing)
)