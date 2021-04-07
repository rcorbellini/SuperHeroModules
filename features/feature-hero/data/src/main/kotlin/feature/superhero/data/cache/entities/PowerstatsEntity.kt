package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Powerstats

@Entity
data class PowerstatsEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int,
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