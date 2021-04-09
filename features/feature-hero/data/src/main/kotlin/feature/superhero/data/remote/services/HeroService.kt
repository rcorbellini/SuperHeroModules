package feature.superhero.data.remote.services

import feature.superhero.data.remote.entities.HeroEntity
import feature.superhero.data.remote.entities.SearchWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {

    @GET("{id}")
    suspend fun getById(@Path(value = "id", encoded = true) id: Int): HeroEntity


    @GET("search/{query}")
    suspend fun search(@Path(value = "query", encoded = true) query: String): SearchWrapper
}