package feature.superhero.data.entities

import feature.superhero.domain.models.Work

data class WorkEntity(
    val occupation: String,
    val base: String
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