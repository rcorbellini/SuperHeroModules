package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import library.domain.core.remote.RemoteApiExceptions

internal interface GetByIdUseCase {
    suspend fun execute(id: Int): Result<Hero>
}

class GetByIdUseCaseImp(private val repository: HeroRepository) : GetByIdUseCase {

    override suspend fun execute(id: Int) : Result<Hero> {
        try {
           return  Result.success(repository.getById(id))
        } catch (exception: Exception) {
            if(exception is RemoteApiExceptions){
                return Result.failure(exception)
            }
            return Result.failure(exception)
        }
    }
}