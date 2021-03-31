package feature.superhero.presentation.models

import feature.superhero.domain.models.Image

data class ImagePresentation(
    val url: String
) {
    fun toModel() = Image(
        url = url
    )
}

fun Image.toPresentation() = ImagePresentation(url = url)