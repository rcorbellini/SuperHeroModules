package feature.superhero.domain.repositories

import feature.superhero.domain.models.Hero

interface HeroRepository {
    suspend fun getById(id : Int): Hero
}