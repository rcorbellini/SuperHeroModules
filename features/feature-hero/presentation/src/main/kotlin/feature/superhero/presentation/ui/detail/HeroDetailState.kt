package feature.superhero.presentation.ui.detail

import feature.superhero.presentation.models.HeroPresentation

data class HeroDetailState(
    val complete : Boolean = false,
    val error: String? = "",
    val hero: HeroPresentation? = null
)