package feature.superhero.data.repositories

import feature.superhero.data.remote.services.HeroService
import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.flow
import library.domain.core.exceptions.RemoteApiExceptions

class HeroRepositoryImp(private val heroService: HeroService) : HeroRepository {
    override suspend fun getById(id: Int) =
        flow<Result<Hero>> {
            try {
                emit(Result.success(heroService.getById(id).toModel()))
            } catch (e: Exception) {
                //TODO make a better error handler (by instance type)
                emit(Result.failure(RemoteApiExceptions()))
            }
        }

    override suspend fun loadAllPaged(offset: Int, limit: Int) =
        flow<Result<List<Hero>>> {
            try {
                val limitOffset = offset + limit
                val range = (offset..limitOffset).toList()

                emit(Result.success(range.map {
                    heroService.getById(it).toModel()
                }))
            } catch (e: Exception) {
                //TODO make a better error handler (by instance type)
                emit(Result.failure(RemoteApiExceptions()))
            }
        }
}