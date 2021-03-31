package feature.superhero.presentation.models

import feature.superhero.domain.models.Work

data class WorkPresentation(
    val occupation: String,
    val base: String
) {
    fun toModel() = Work(
        occupation = occupation,
        base = base,
    )
}

fun Work.toPresentation() = WorkPresentation(
    occupation = occupation,
    base = base,
)