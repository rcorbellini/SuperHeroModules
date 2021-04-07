package feature.superhero.data.cache.entities

import androidx.room.Embedded
import androidx.room.Relation
import feature.superhero.domain.models.Hero

data class HeroFullEntity(
    @Embedded val hero: HeroEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val powerstats: PowerstatsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val biography: BiographyEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val appearance: AppearanceEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val work: WorkEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val connections: ConnectionsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val image: ImageEntity
){

    fun toModel() = Hero(
        id = hero.id,
        response = hero.response,
        name = hero.name,
        powerstats = powerstats.toModel(),
        biography = biography.toModel(),
        appearance = appearance.toModel(),
        work = work.toModel(),
        connections = connections.toModel(),
        image = image.toModel(),
    )
}

fun Hero.toCacheEntity() = HeroFullEntity(
    hero = HeroEntity(id = id, response = response, name= name),
    powerstats = powerstats.toEntity().copy(heroId = id),
    biography = biography.toEntity().copy(heroId = id),
    appearance = appearance.toEntity().copy(heroId = id),
    work = work.toEntity().copy(heroId = id),
    connections = connections.toEntity().copy(heroId = id),
    image = image.toEntity().copy(heroId = id),
)