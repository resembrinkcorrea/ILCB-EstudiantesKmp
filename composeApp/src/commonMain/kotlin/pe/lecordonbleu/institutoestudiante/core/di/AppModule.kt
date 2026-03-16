package pe.lecordonbleu.institutoestudiante.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import pe.lecordonbleu.institutoestudiante.data.repository.LoginRepositoryImpl
import pe.lecordonbleu.institutoestudiante.SettingsStorage
import pe.lecordonbleu.institutoestudiante.getSettingsStorage
import pe.lecordonbleu.institutoestudiante.domain.repository.LoginRepository
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginViewModel

fun appModule() = module {

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    single<SettingsStorage> { getSettingsStorage() }

    single<LoginRepository> { LoginRepositoryImpl(get()) }

    factory { LoginViewModel(get()) }
}
