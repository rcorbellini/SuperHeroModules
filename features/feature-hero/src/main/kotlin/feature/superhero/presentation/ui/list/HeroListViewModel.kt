package feature.superhero.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.superhero.domain.models.Hero
import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllParams
import feature.superhero.presentation.models.HeroPresentation
import feature.superhero.presentation.models.toPresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val loadAllPagedUseCase: LoadAllPagedUseCase
) : ViewModel() {

    private val _listState = MutableLiveData<HeroListState>()

    val listState : LiveData<HeroListState>
            get() = _listState

    init {
        onInit()
    }

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
        viewModelScope.launch {
            val offset = _listState.value?.listHero?.size ?: 0
            loadAllPagedUseCase.execute(LoadAllParams(offset = offset+1))
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

    private fun onInit(){
        _listState.value = HeroListState(complete = true, error = null, listHero = emptyList())
    }

    private fun onLoading(boolean: Boolean) {
        _listState.value = _listState.value?.copy(complete = boolean)
    }

    private fun onSuccessLoadAll(hero: Hero) {
        val oldList: List<HeroPresentation> = _listState.value?.listHero ?: emptyList()
        val newList: List<HeroPresentation> = listOf(hero.toPresentation())
        val currentList = oldList + newList
        _listState.value = _listState.value?.copy(listHero = currentList)
    }

    private fun onFail(error: Throwable) {
        _listState.value = _listState.value?.copy(error = error.message)
    }
}