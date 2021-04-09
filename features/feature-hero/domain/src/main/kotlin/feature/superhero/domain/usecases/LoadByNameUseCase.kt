package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.flow.Flow
import library.domain.core.usecases.UseCase
import library.domain.core.usecases.UseCaseParam

interface LoadByNameUseCase : UseCase<List<Hero>, LoadByNameParams> {
    override suspend fun execute(params: LoadByNameParams): Flow<Result<List<Hero>>>
}

class LoadByNameUseCaseImp(private val repository: HeroRepository) : LoadByNameUseCase {
    override suspend fun execute(params: LoadByNameParams) =
        repository.loadByName(query = params.query)
}

data class LoadByNameParams(
    val query: String,
) : UseCaseParam()