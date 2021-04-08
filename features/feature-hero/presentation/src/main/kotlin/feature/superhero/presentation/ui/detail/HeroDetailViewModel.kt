package feature.superhero.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.superhero.domain.models.Hero
import feature.superhero.domain.usecases.GetByIdParam
import feature.superhero.domain.usecases.GetByIdUseCase
import feature.superhero.presentation.models.toPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HeroDetailViewModel(
    private val getByIdUseCase: GetByIdUseCase
) : ViewModel() {

    private val _heroDetailState =
        MutableStateFlow(HeroDetailState(complete = false, error = null, hero = null))

    private var job: Job? = null

    val detailState: StateFlow<HeroDetailState>
        get() = _heroDetailState

    fun dispatchEvent(heroListEvent: HeroDetailEvent) {
        when (heroListEvent) {
            is HeroDetailEvent.Load -> load(heroListEvent.id)
        }
    }

    private fun load(id: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            getByIdUseCase.execute(GetByIdParam(id = id))
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
        _heroDetailState.value = _heroDetailState.value.copy(complete = !loading)
    }

    private fun onSuccessLoadAll(hero: Hero) {
        _heroDetailState.value = _heroDetailState.value.copy(hero = hero.toPresentation())
    }

    private fun onFail(error: Throwable) {
        _heroDetailState.value = _heroDetailState.value.copy(error = error.message)
    }
}