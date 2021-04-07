package feature.superhero.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import feature.superhero.data.cache.entities.AppearanceEntity
import feature.superhero.data.cache.entities.BiographyEntity
import feature.superhero.data.cache.entities.ConnectionsEntity
import feature.superhero.data.cache.entities.HeroEntity
import feature.superhero.data.cache.entities.HeroFullEntity
import feature.superhero.data.cache.entities.ImageEntity
import feature.superhero.data.cache.entities.PowerstatsEntity
import feature.superhero.data.cache.entities.WorkEntity

@Dao
abstract class HeroDao {

    @Query("SELECT * FROM hero ORDER BY id DESC")
    abstract suspend fun  getHeroes(): List<HeroFullEntity>


    @Query("SELECT * FROM hero where id=:id")
    abstract suspend fun  getHero(id: Int): HeroFullEntity?

    @Transaction
    open suspend fun insertHeroFull(obj: HeroFullEntity) {
        insertHero(obj.hero)
        insertAppearence(obj.appearance)
        insertBio(obj.biography)
        insertConnection(obj.connections)
        insertImage(obj.image)
        insertPowerstats(obj.powerstats)
        insertWork(obj.work)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHero(entity: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAppearence(entity: AppearanceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertBio(entity: BiographyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertConnection(entity: ConnectionsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertImage(entity: ImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPowerstats(entity: PowerstatsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertWork(entity: WorkEntity)
}
