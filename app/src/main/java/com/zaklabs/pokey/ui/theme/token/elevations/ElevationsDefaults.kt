package com.zaklabs.pokey.ui.theme.token.elevations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import com.zaklabs.pokey.R

internal object ElevationsDefaults {

    val V1
        @Composable @ReadOnlyComposable
        get() = Elevations(
            none = dimensionResource(R.dimen.elevation_none),
            xSmall = dimensionResource(R.dimen.elevation_xsmall),
            small = dimensionResource(R.dimen.elevation_small),
            medium = dimensionResource(R.dimen.elevation_medium),
            large = dimensionResource(R.dimen.elevation_large),
            xLarge = dimensionResource(R.dimen.elevation_xlarge),
            max = dimensionResource(R.dimen.elevation_max),
        )
}
