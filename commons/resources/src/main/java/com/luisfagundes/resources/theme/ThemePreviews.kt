package com.luisfagundes.resources.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light theme",
    showBackground = true,
    backgroundColor = LIGHT_SURFACE_COLOR_VALUE,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark theme",
    showBackground = true,
    backgroundColor = DARK_SURFACE_COLOR_VALUE,
)
annotation class ThemePreviews