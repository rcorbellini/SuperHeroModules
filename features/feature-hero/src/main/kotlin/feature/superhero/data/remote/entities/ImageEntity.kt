package feature.superhero.data.entities

import feature.superhero.domain.models.Image

data class ImageEntity(
    val url: String
) {
    fun toModel() = Image(
        url = url
    )
}

fun Image.toEntity() = ImageEntity(url = url)