package feature.superhero.data.entities

import feature.superhero.domain.models.Appearance

data class AppearanceEntity(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    val eyeColor: String,
    val hairColor: String
) {
    fun toModel() = Appearance(
        gender = gender,
        race = race,
        height = height,
        weight = weight,
        eyeColor = eyeColor,
        hairColor = hairColor,
    )
}

fun Appearance.toEntity() = AppearanceEntity(
    gender = gender,
    race = race,
    height = height,
    weight = weight,
    eyeColor = eyeColor,
    hairColor = hairColor,
)