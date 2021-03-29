package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero

internal abstract class GetByIdUseCase {
    abstract suspend fun execute(id : Int): Result<Hero>
}

