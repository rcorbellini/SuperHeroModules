package feature.superhero.di

import feature.superhero.data.repositories.HeroRepositoryImp
import feature.superhero.domain.repositories.HeroRepository
import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllPagedUseCaseImp
import feature.superhero.presentation.ui.list.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val heroViewModel = module {

    viewModel {
        HeroListViewModel(
            loadAllPagedUseCase = get()
        )
    }
}

val heroUseCase = module {
    factory {
        LoadAllPagedUseCaseImp( repository = get()) as LoadAllPagedUseCase
    }
}

val heroRepository = module {
    factory {
        HeroRepositoryImp( heroService = get()) as HeroRepository
    }
}
