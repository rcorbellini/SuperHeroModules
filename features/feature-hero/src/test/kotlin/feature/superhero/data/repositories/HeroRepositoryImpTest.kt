package feature.superhero.data.repositories

import feature.superhero.data.remote.entities.HeroEntity
import feature.superhero.data.remote.services.HeroService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import library.domain.core.exceptions.RemoteApiExceptions
import library.test.core.makeRandomInstance
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HeroRepositoryImpTest {
    @Mock
    private lateinit var serviceMock: HeroService
    private lateinit var repositoryImp: HeroRepositoryImp

    @Before
    fun `HeroRepositoryImpTest before each`() {
        MockitoAnnotations.initMocks(this)
        repositoryImp = HeroRepositoryImp(heroService = serviceMock)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `GetById must return a hero`() = runBlockingTest {
        //arrange
        val expected = makeRandomInstance<HeroEntity>()
        val (id) = expected
        whenever(serviceMock.getById(id)).thenReturn(expected)

        //act
        repositoryImp.getById(id).collect { result ->

            //assert
            assertEquals(expected.toModel(), result.getOrNull())
        }

    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `GetById must throw a Remote API Exception when a Exception rise`() = runBlockingTest {
        //arrange
        val id = 1
        whenever(serviceMock.getById(any())).thenThrow(RuntimeException())

        //act
        repositoryImp.getById(id).collect { result ->
            //assert
            assertTrue( result.exceptionOrNull() is RemoteApiExceptions)
        }

    }
}