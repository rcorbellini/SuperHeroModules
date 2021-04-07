package feature.superhero.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.superhero.domain.models.Hero
import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllParams
import feature.superhero.presentation.models.HeroPresentation
import feature.superhero.presentation.models.toPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val loadAllPagedUseCase: LoadAllPagedUseCase
) : ViewModel() {

    private val _listState = MutableStateFlow(HeroListState(complete = false, error = null, listHero = emptyList()))

    private var job : Job? = null

    val listState : StateFlow<HeroListState>
            get() = _listState

    fun dispatchEvent(heroListEvent: HeroListEvent){
        when(heroListEvent){
            is HeroListEvent.LoadMore -> loadMore()
            is HeroListEvent.Search -> search(heroListEvent.query)
        }
    }

    private fun search(query: String){
        throw  NotImplementedError()
    }

    private fun loadMore() {
        job?.cancel()
        job = viewModelScope.launch {
            val offset = _listState.value.listHero.size
            loadAllPagedUseCase.execute(LoadAllParams(offset = offset+1, limit = 20))
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


    private fun onLoading(loading: Boolean) {
        _listState.value = _listState.value.copy(complete = !loading)
    }

    private fun onSuccessLoadAll(hero: Hero) {
        val oldList: List<HeroPresentation> = _listState.value.listHero ?: emptyList()
        val newList: List<HeroPresentation> = listOf(hero.toPresentation())
        val currentList = oldList + newList
        _listState.value = _listState.value.copy(listHero = currentList)
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value.copy(error = error.message)
    }
}