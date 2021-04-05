package feature.superhero.presentation.models

import feature.superhero.domain.models.Hero
import feature.superhero.presentation.models.BiographyPresentation
import feature.superhero.presentation.models.toPresentation

data class HeroPresentation(
    val id: Int,
    val response: String,
    val name: String,
    val powerstats: PowerstatsPresentation,
    val biography: BiographyPresentation,
    val appearance: AppearancePresentation,
    val work: WorkPresentation,
    val connections: ConnectionsPresentation,
    val image: ImagePresentation
) {
    fun toModel() = Hero(
        id = id,
        response = response,
        name = name,
        powerstats = powerstats.toModel(),
        biography = biography.toModel(),
        appearance = appearance.toModel(),
        work = work.toModel(),
        connections = connections.toModel(),
        image = image.toModel(),
    )
}

fun Hero.toPresentation() = HeroPresentation(
    id = id,
    response = response,
    name = name,
    powerstats = powerstats.toPresentation(),
    biography = biography.toPresentation(),
    appearance = appearance.toPresentation(),
    work = work.toPresentation(),
    connections = connections.toPresentation(),
    image = image.toPresentation(),
)