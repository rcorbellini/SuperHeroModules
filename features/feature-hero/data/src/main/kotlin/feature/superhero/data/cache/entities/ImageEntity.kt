package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Image

@Entity
data class ImageEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val url: String,
) {
    fun toModel() = Image(
        url = url
    )
}

fun Image.toEntity() = ImageEntity(url = url)