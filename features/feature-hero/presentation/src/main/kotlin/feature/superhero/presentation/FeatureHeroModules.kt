package feature.superhero.presentation

import feature.superhero.presentation.ui.detail.HeroDetailViewModel
import feature.superhero.presentation.ui.list.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val heroPresentation = module {

    viewModel {
        HeroListViewModel(
            loadAllPagedUseCase = get()
        )
    }

    viewModel {
        HeroDetailViewModel(
            getByIdUseCase = get()
        )
    }
}
