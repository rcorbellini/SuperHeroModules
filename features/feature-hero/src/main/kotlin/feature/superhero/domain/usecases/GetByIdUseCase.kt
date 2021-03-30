package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository

internal interface GetByIdUseCase {
    suspend fun execute(id: Int): Result<Hero>
}

class GetByIdUseCaseImp(private val repository: HeroRepository) : GetByIdUseCase {

    override suspend fun execute(id: Int) = try {
        Result.success(repository.getById(id))
    } catch (exception: Exception) {
        //todo Melhorar tratamento da exception
        Result.failure(exception)
    }
}