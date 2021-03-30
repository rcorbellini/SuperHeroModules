package feature.superhero.data.remote.entities

import feature.superhero.domain.models.Biography

data class BiographyEntity(
    val fullName: String,
    val alterEgos: String,
    val aliases: List<String>,
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
) {
    fun toModel() = Biography(
        fullName = fullName,
        alterEgos = alterEgos,
        aliases = aliases,
        placeOfBirth = placeOfBirth,
        firstAppearance = firstAppearance,
        publisher = publisher,
        alignment = alignment,
    )
}

fun Biography.toEntity() = BiographyEntity(
    fullName = fullName,
    alterEgos = alterEgos,
    aliases = aliases,
    placeOfBirth = placeOfBirth,
    firstAppearance = firstAppearance,
    publisher = publisher,
    alignment = alignment,
)