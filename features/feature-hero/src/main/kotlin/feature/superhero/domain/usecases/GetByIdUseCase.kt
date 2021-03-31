package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow

interface GetByIdUseCase {
    suspend fun execute(params: ParamGetById): Flow<Result<Hero>>
}

class GetByIdUseCaseImp(private val repository: HeroRepository) : GetByIdUseCase {
    override suspend fun execute(params: ParamGetById) = repository.getById(id = params.id)
}

class ParamGetById(
    val id: Int
)