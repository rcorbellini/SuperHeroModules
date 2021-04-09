package feature.superhero.presentation.ui.list

import feature.superhero.presentation.models.HeroPresentation

data class HeroListState(
    val complete : Boolean = false,
    val error: String? = "",
    val listHeroes: List<HeroPresentation> = emptyList(),
    val listSearchHeroes: List<HeroPresentation> = emptyList()
)