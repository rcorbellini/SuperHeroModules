package feature.superhero.presentation.ui.detail

sealed class HeroDetailEvent {
    data class Load(val id: Int) : HeroDetailEvent()
}