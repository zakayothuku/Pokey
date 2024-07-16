package com.zaklabs.pokey.ui.theme.token.spacers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import com.zaklabs.pokey.R

internal object SpacersDefaults {
    val V1
        @Composable @ReadOnlyComposable
        get() = Spacers(
            xxxSmall = dimensionResource(R.dimen.dimen_xxxsmall),
            xxSmall = dimensionResource(R.dimen.dimen_xxsmall),
            xSmall = dimensionResource(R.dimen.dimen_xsmall),
            small = dimensionResource(R.dimen.dimen_small),
            medium = dimensionResource(R.dimen.dimen_medium),
            large = dimensionResource(R.dimen.dimen_large),
            xLarge = dimensionResource(R.dimen.dimen_xlarge),
            xxLarge = dimensionResource(R.dimen.dimen_xxlarge),
        )
}
