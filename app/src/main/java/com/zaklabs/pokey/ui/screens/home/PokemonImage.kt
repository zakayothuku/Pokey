package com.zaklabs.pokey.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.zaklabs.pokey.R
import com.zaklabs.pokey.ui.theme.PokeyTheme

/**
 * Pokemon image
 *
 * @param modifier
 * @param url
 * @param size
 */
@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    size: Dp,
    onUpdatePalette: (Palette?) -> Unit = { },
) {
    GlideImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(contentScale = ContentScale.Inside),
        previewPlaceholder = painterResource(R.drawable.ic_pokemon),
        component = rememberImageComponent {
            +CrossfadePlugin()
            +ShimmerPlugin(
                Shimmer.Resonate(
                    baseColor = Color.Transparent,
                    highlightColor = Color.LightGray,
                ),
            )

            if (!LocalInspectionMode.current) {
                +PalettePlugin(
                    imageModel = imageUrl,
                    useCache = true,
                    paletteLoadedListener = { onUpdatePalette(it) },
                )
            }
        },
        modifier = modifier
            .padding(top = PokeyTheme.spacers.large)
            .size(size),
    )
}
