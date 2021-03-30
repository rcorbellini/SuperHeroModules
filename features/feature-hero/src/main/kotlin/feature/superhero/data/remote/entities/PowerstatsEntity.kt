package feature.superhero.data.entities

import feature.superhero.domain.models.Powerstats

data class PowerstatsEntity(
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

fun Powerstats.toEntity() = PowerstatsEntity(
    intelligence = intelligence,
    strength = strength,
    speed = speed,
    durability = durability,
    power = power,
    combat = combat,
)