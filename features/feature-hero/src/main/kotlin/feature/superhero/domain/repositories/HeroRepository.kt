package feature.superhero.domain.repositories

import feature.superhero.domain.models.Hero
import kotlinx.coroutines.flow.Flow

interface HeroRepository {
    suspend fun getById(id: Int): Flow<Result<Hero>>

    suspend fun loadAllPaged(offset: Int, limit: Int): Flow<Result<List<Hero>>>
}