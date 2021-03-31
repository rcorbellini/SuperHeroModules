package feature.superhero.presentation.models

import feature.superhero.domain.models.Powerstats

data class PowerstatsPresentation(
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int
) {
    fun toModel() = Powerstats(
        intelligence = intelligence,
        strength = strength,
        speed = speed,
        durability = durability,
        power = power,
        combat = combat,
    )
}

fun Powerstats.toPresentation() = PowerstatsPresentation(
    intelligence = intelligence,
    strength = strength,
    speed = speed,
    durability = durability,
    power = power,
    combat = combat,
)