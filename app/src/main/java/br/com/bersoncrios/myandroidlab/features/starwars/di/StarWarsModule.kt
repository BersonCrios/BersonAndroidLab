package br.com.bersoncrios.myandroidlab.features.starwars.di

import br.com.bersoncrios.myandroidlab.features.starwars.network.StarWarsApi
import br.com.bersoncrios.myandroidlab.features.starwars.repository.StarWarsRepository
import br.com.bersoncrios.myandroidlab.features.starwars.viewmodel.StarWarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val startWarsModule = module {
    fun provideStartWarsApi(retrofit: Retrofit) :StarWarsApi{
        return retrofit.create(StarWarsApi::class.java)
    }

    single { provideStartWarsApi(get()) }
    single { StarWarsRepository(get()) }

    viewModel {
        StarWarsViewModel(get(), get());
    }
}