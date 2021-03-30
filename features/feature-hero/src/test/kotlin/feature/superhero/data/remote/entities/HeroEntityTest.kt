package feature.superhero.data.remote.entities

import library.test.core.makeRandomInstance
import org.junit.Test
import kotlin.test.assertEquals

class HeroEntityTest {

    @ExperimentalStdlibApi
    @Test
    fun `HeroEntity should success mapper to Hero (Domain)`(){
        //arrange
        val entity = makeRandomInstance<HeroEntity>()

        //act
        val result = entity.toModel()

        //assert
        assertEquals(entity.id, result.id)
        assertEquals(entity.response, result.response)
        assertEquals(entity.name, result.name)
        assertEquals(entity.powerstats.toModel(), result.powerstats)
        assertEquals(entity.biography.toModel(), result.biography)
        assertEquals(entity.appearance.toModel(), result.appearance)
        assertEquals(entity.work.toModel(), result.work)
        assertEquals(entity.connections.toModel(), result.connections)
        assertEquals(entity.image.toModel(), result.image)
    }
}