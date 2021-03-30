package feature.superhero.data.remote.entities

import feature.superhero.domain.models.Hero

data class HeroEntity(
    val id: Int,
    val response: String,
    val name: String,
    val powerstats: PowerstatsEntity,
    val biography: BiographyEntity,
    val appearance: AppearanceEntity,
    val work: WorkEntity,
    val connections: ConnectionsEntity,
    val image: ImageEntity
) {

    fun toModel() = Hero(
        id = id,
        response = response,
        name = name,
        powerstats = powerstats.toModel(),
        biography = biography.toModel(),
        appearance = appearance.toModel(),
        work = work.toModel(),
        connections = connections.toModel(),
        image = image.toModel(),
    )
}

fun Hero.toEntity() = HeroEntity(
    id = id,
    response = response,
    name = name,
    powerstats = powerstats.toEntity(),
    biography = biography.toEntity(),
    appearance = appearance.toEntity(),
    work = work.toEntity(),
    connections = connections.toEntity(),
    image = image.toEntity(),
)