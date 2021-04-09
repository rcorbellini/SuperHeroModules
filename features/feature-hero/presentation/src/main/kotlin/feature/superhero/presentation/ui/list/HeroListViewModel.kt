package feature.superhero.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.superhero.domain.models.Hero
import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllParams
import feature.superhero.domain.usecases.LoadByNameParams
import feature.superhero.domain.usecases.LoadByNameUseCase
import feature.superhero.presentation.models.HeroPresentation
import feature.superhero.presentation.models.toPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val loadAllPagedUseCase: LoadAllPagedUseCase,
    private val laodByNameUseCase: LoadByNameUseCase
) : ViewModel() {

    private val _listState =
        MutableStateFlow(
            HeroListState(
                complete = false,
                error = null,
                listHeroes = emptyList(),
                listSearchHeroes = emptyList()
            )
        )

    val listState: StateFlow<HeroListState>
        get() = _listState

    private var jobList: Job? = null
    private var jobQuery: Job? = null

    fun dispatchEvent(heroListEvent: HeroListEvent) {
        when (heroListEvent) {
            is HeroListEvent.LoadMore -> loadMore()
            is HeroListEvent.Search -> search(heroListEvent.query)
        }
    }

    private fun search(query: String) {
        jobQuery?.cancel()
        jobQuery = viewModelScope.launch {
            delay(500)
            if(query.length<THRESHOLD){
                clearSearchList()
                return@launch
            }
            laodByNameUseCase.execute(LoadByNameParams(query = query))
                .onStart { onLoading(true) }
                .onCompletion { onLoading(false) }
                .collect { result ->
                    result.fold(onFailure = ::onFail, onSuccess = ::onSuccessLoadByName)
                }
        }
    }

    private fun loadMore() {
        //avoid to load more when searching
        if(_listState.value.listSearchHeroes.isNotEmpty()){
            return
        }
        jobList?.cancel()
        jobList = viewModelScope.launch {
            val offset = _listState.value.listHeroes.size
            loadAllPagedUseCase.execute(LoadAllParams(offset = offset + 1, limit = 20))
                .onStart {
                    onLoading(true)
                }
                .onCompletion {
                    onLoading(false)
                }
                .collect { result ->
                    result.fold(onFailure = ::onFail, onSuccess = ::onSuccessLoadAll)
                }
        }
    }

    private fun clearSearchList(){
        _listState.value = _listState.value.copy(listSearchHeroes = emptyList())
    }

    private fun onLoading(loading: Boolean) {
        _listState.value = _listState.value.copy(complete = !loading)
    }

    private fun onSuccessLoadByName(heroes: List<Hero>) {
        _listState.value =
            _listState.value.copy(listSearchHeroes = heroes.map { it.toPresentation() })
    }

    private fun onSuccessLoadAll(hero: Hero) {
        val oldList: List<HeroPresentation> = _listState.value.listHeroes ?: emptyList()
        val newList: List<HeroPresentation> = listOf(hero.toPresentation())
        val currentList = oldList + newList
        _listState.value = _listState.value.copy(listHeroes = currentList)
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value.copy(error = error.message)
    }

    companion object{
        val THRESHOLD = 3
    }
}