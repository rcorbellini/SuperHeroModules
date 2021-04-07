package feature.superhero.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import feature.superhero.data.cache.dao.HeroDao
import feature.superhero.data.cache.entities.AppearanceEntity
import feature.superhero.data.cache.entities.BiographyEntity
import feature.superhero.data.cache.entities.ConnectionsEntity
import feature.superhero.data.cache.entities.HeroEntity
import feature.superhero.data.cache.entities.ImageEntity
import feature.superhero.data.cache.entities.PowerstatsEntity
import feature.superhero.data.cache.entities.WorkEntity

@Database(
    entities = [HeroEntity::class, AppearanceEntity::class,
        BiographyEntity::class, ConnectionsEntity::class,
        ImageEntity::class, PowerstatsEntity::class,
        WorkEntity::class],
    version = 1
)
abstract class SuperHeroDatabase : RoomDatabase(){
    abstract fun heroDao(): HeroDao
}