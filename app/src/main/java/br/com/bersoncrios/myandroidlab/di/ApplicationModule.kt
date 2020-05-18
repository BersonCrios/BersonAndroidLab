package br.com.bersoncrios.myandroidlab.di

import android.content.Context
import br.com.bersoncrios.myandroidlab.BuildConfig
import br.com.bersoncrios.myandroidlab.core.viewmodel.CoroutineContextProvider
import br.com.bersoncrios.myandroidlab.core.viewmodel.ResourceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
}

val courotinesModule = module {
    fun providerCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }
    single { providerCoroutineContextProvider() }
}

val networkModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        return okHttpClientBuilder.build()
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SWAPI_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager(
            context
        )
    }

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideResourceManager(get()) }
}

