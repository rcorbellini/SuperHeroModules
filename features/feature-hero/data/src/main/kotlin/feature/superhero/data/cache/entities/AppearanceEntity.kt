package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Appearance

@Entity
data class AppearanceEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val gender: String,
    val race: String,
    val height: String,
    val weight: String,
    val eyeColor: String,
    val hairColor: String,
) {
    fun toModel() = Appearance(
        gender = gender,
        race = race,
        height = arrayListOf(height),
        weight = arrayListOf(weight),
        eyeColor = eyeColor,
        hairColor = hairColor,
    )
}

fun Appearance.toEntity() = AppearanceEntity(
    gender = gender,
    race = race,
    height = height[0],
    weight = weight[0],
    eyeColor = eyeColor,
    hairColor = hairColor,
)