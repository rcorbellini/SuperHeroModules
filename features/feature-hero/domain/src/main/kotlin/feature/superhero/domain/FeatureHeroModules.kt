package feature.superhero.domain

import feature.superhero.domain.usecases.LoadAllPagedUseCase
import feature.superhero.domain.usecases.LoadAllPagedUseCaseImp
import org.koin.dsl.module

val heroDomain = module {
    factory<LoadAllPagedUseCase> {
        LoadAllPagedUseCaseImp(repository = get())
    }
}
