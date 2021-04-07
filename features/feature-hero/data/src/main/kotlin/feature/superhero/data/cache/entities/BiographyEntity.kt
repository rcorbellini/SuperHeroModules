package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Biography

@Entity
data class BiographyEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val fullName: String,
    val alterEgos: String,
    val aliases: String,
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String,
) {
    fun toModel() = Biography(
        fullName = fullName,
        alterEgos = alterEgos,
        aliases = listOf(aliases) ,
        placeOfBirth = placeOfBirth,
        firstAppearance = firstAppearance,
        publisher = publisher,
        alignment = alignment,
    )
}

fun Biography.toEntity() = BiographyEntity(
    fullName = fullName,
    alterEgos = alterEgos,
    aliases = aliases[0],
    placeOfBirth = placeOfBirth,
    firstAppearance = firstAppearance,
    publisher = publisher,
    alignment = alignment,
)