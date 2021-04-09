package feature.superhero.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class HeroEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
)