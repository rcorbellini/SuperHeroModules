package feature.superhero.data.entities

import feature.superhero.domain.models.Connections

data class ConnectionsEntity(
    val groupAffiliation: String,
    val relatives: String
) {
    fun toModel() = Connections(
        groupAffiliation = groupAffiliation,
        relatives = relatives,
    )
}

fun Connections.toEntity() =  ConnectionsEntity(
    groupAffiliation = groupAffiliation,
    relatives = relatives,
)