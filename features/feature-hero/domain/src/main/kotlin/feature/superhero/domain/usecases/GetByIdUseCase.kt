package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow
import library.domain.core.usecases.UseCase
import library.domain.core.usecases.UseCaseParam

interface GetByIdUseCase : UseCase<Hero, GetByIdParam> {
    override suspend fun execute(params: GetByIdParam): Flow<Result<Hero>>
}

class GetByIdUseCaseImp(private val repository: HeroRepository) : GetByIdUseCase {
    override suspend fun execute(params: GetByIdParam) = repository.getById(id = params.id)
}

class GetByIdParam(
    val id: Int
) : UseCaseParam()