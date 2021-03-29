package feature.superhero.domain.usecases

import feature.superhero.domain.models.Hero
import feature.superhero.domain.repositories.HeroRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import library.test.core.makeRandomInstance
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class GetByIdUseCaseTest {

    @Mock
    private lateinit var  service : HeroRepository
    private lateinit var getByIdUseCaseImp: GetByIdUseCaseImp

    @Before
    fun `GetById before tests`(){
        MockitoAnnotations.openMocks(this)
        getByIdUseCaseImp = GetByIdUseCaseImp(service)
    }

    @ExperimentalCoroutinesApi
    @ExperimentalStdlibApi
    @Test
    fun `GetById must return a hero`() = runBlockingTest {
        //arrange
        val hero = makeRandomInstance<Hero>()
        println(hero)
        val expect = Result.success(hero)
        val (id) = hero
        whenever(service.getById(any())).thenReturn(expect)

        //act
        val result = getByIdUseCaseImp.execute(id)

        //assert
        assertTrue {
            result.isSuccess && expect == result
        }
    }
}