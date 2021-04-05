package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow
import library.domain.core.usecases.UseCase
import library.domain.core.usecases.UseCaseParam

interface LoadAllPagedUseCase : UseCase<Hero, LoadAllParams> {
    override suspend fun execute(params: LoadAllParams): Flow<Result<Hero>>
}

class LoadAllPagedUseCaseImp(private val repository: HeroRepository) : LoadAllPagedUseCase {
    override suspend fun execute(params: LoadAllParams) =
        repository.loadAllPaged(offset = params.offset, limit = params.limit)
}

data class LoadAllParams(
    val offset: Int = 0,
    val limit: Int = 20
) : UseCaseParam()