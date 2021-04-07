package feature.superhero.data.repositories

import feature.superhero.data.cache.dao.HeroDao
import feature.superhero.data.cache.entities.HeroEntity
import feature.superhero.data.cache.entities.HeroFullEntity
import feature.superhero.data.cache.entities.toCacheEntity
import feature.superhero.data.cache.entities.toEntity
import feature.superhero.data.remote.services.HeroService
import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.flow
import library.domain.core.exceptions.RemoteApiExceptions

class HeroRepositoryImp(
    private val heroService: HeroService,
    private val heroDao: HeroDao
) : HeroRepository {

    override suspend fun getById(id: Int) =
        flow<Result<Hero>> {
            try {
                emit(Result.success(getAndCache(id)))
            } catch (e: Exception) {
                //TODO make a better error handler (by instance type)
                emit(Result.failure(RemoteApiExceptions()))
            }
        }

    override suspend fun loadAllPaged(offset: Int, limit: Int) =
        flow<Result<Hero>> {
            try {
                val limitOffset = offset + limit
                val range = (offset..limitOffset).toList()

                range.map {
                    emit(Result.success(getAndCache(it)))
                }
            } catch (e: Exception) {
                //TODO make a better error handler (by instance type)
                e.printStackTrace()
                emit(Result.failure(RemoteApiExceptions()))
            }
        }

    private suspend fun getAndCache(id: Int): Hero {
        //return cached
        heroDao.getHero(id = id)?.run {
            return@getAndCache this.toModel()
        }

        //caching and returning remote
        heroService.getById(id).toModel().run {
            heroDao.insertHeroFull(
              this.toCacheEntity()
            )
            return@getAndCache this
        }
    }
}