package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import library.domain.core.exceptions.RemoteApiExceptions
import library.test.core.makeRandomInstance
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetByIdUseCaseTest {

    @Mock
    private lateinit var  service : HeroRepository
    private lateinit var getByIdUseCaseImp: GetByIdUseCaseImp

    @Before
    fun `GetById before each`(){
        MockitoAnnotations.initMocks(this)
        getByIdUseCaseImp = GetByIdUseCaseImp(service)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `GetById must return a success hero`() = runBlockingTest {
        //arrange
        val hero = makeRandomInstance<Hero>()
        val (id) = hero
        whenever(service.getById(id)).thenReturn(flow{emit(Result.success(hero))})

        //act
        getByIdUseCaseImp.execute(params = GetByIdParam(id)).collect { result ->

            //assert
            assertEquals(Result.success(hero), result)
        }

    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `GetById must return a failure, when exception`() = runBlockingTest {
        //arrange
        val hero = makeRandomInstance<Hero>()
        val (id) = hero
        whenever(service.getById(id)).thenReturn(flow<Result<Hero>>{ emit(Result.failure(
            RemoteApiExceptions()
        ))})

        //act
        getByIdUseCaseImp.execute(params = GetByIdParam(id)).collect { result ->
            //assert
            assertTrue(result.isFailure && result.exceptionOrNull() is RemoteApiExceptions)
        }

    }
}