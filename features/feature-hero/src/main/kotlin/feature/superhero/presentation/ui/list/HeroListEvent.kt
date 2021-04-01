package feature.superhero.presentation.ui.list

sealed class HeroListEvent {
    object LoadMore : HeroListEvent()
    data class Search(val query: String) : HeroListEvent()
}