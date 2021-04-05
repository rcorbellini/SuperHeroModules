package feature.superhero.presentation.models

import feature.superhero.domain.models.Connections

data class ConnectionsPresentation(
    val groupAffiliation: String,
    val relatives: String
) {
    fun toModel() = Connections(
        groupAffiliation = groupAffiliation,
        relatives = relatives,
    )
}

fun Connections.toPresentation() =  ConnectionsPresentation(
    groupAffiliation = groupAffiliation,
    relatives = relatives,
)