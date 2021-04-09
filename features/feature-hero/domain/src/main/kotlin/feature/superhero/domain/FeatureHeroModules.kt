package feature.superhero.domain

import feature.superhero.domain.usecases.GetByIdUseCase
import feature.superhero.domain.usecases.GetByIdUseCaseImp
import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllPagedUseCaseImp
import feature.superhero.domain.usecases.LoadByNameUseCase
import feature.superhero.domain.usecases.LoadByNameUseCaseImp
import org.koin.dsl.module

val heroDomain = module {
    factory<LoadAllPagedUseCase> {
        LoadAllPagedUseCaseImp(repository = get())
    }

    factory<GetByIdUseCase> {
        GetByIdUseCaseImp(repository = get())
    }

    factory<LoadByNameUseCase> {
        LoadByNameUseCaseImp(repository = get())
    }
}
