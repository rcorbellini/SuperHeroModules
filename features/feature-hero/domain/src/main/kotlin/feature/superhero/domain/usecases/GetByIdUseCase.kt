package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow
import library.domain.core.usecases.UseCase
import library.domain.core.usecases.UseCaseParam

interface GetByIdUseCase : UseCase<Hero, ParamGetById> {
    override suspend fun execute(params: ParamGetById): Flow<Result<Hero>>
}

class GetByIdUseCaseImp(private val repository: HeroRepository) : GetByIdUseCase {
    override suspend fun execute(params: ParamGetById) = repository.getById(id = params.id)
}

class ParamGetById(
    val id: Int
) : UseCaseParam()