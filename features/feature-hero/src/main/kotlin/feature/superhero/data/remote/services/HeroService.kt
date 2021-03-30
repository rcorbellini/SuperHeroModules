package feature.superhero.data.remote.services

import feature.superhero.data.remote.entities.HeroEntity

interface HeroService {
    suspend fun getById(id: Int): HeroEntity
}