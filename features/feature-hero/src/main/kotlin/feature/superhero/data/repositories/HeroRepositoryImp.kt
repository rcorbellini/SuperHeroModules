package feature.superhero.data.repositories

import feature.superhero.data.remote.services.HeroService
import feature.superhero.domain.repositories.HeroRepository
import library.domain.core.remote.RemoteApiExceptions
import java.lang.Exception

class HeroRepositoryImp(private val heroService: HeroService) : HeroRepository {
    override suspend fun getById(id: Int) =
        try {
            heroService.getById(id).toModel()
        }catch (e : Exception){
            throw RemoteApiExceptions()
        }
}