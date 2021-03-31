package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow

interface LoadAllPagedUseCase {
    suspend fun execute(params: LoadAllParams = LoadAllParams()): Flow<Result<List<Hero>>>
}

class LoadAllPagedUseCaseImp(private val repository: HeroRepository) : LoadAllPagedUseCase {

    override suspend fun execute(params: LoadAllParams) =
        repository.loadAllPaged(offset = params.offset, limit = params.limit)
}

data class LoadAllParams(
    val offset: Int = 0,
    val limit: Int = 20
)