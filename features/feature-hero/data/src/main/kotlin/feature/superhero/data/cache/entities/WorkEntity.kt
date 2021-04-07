package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Work

@Entity
data class WorkEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val occupation: String,
    val base: String,
) {
    fun toModel() = Work(
        occupation = occupation,
        base = base,
    )
}

fun Work.toEntity() = WorkEntity(
    occupation = occupation,
    base = base,
)