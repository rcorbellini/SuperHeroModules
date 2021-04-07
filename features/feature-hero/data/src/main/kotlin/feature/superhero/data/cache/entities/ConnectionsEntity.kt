package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import feature.superhero.domain.models.Connections

@Entity
data class ConnectionsEntity(
    @PrimaryKey
    var heroId : Int? = null,
    val groupAffiliation: String,
    val relatives: String,
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