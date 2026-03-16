package pe.lecordonbleu.institutoestudiante.core.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import pe.lecordonbleu.institutoestudiante.data.storage.SettingsStorage
import pe.lecordonbleu.institutoestudiante.data.storage.getSettingsStorage

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

    // ViewModels se agregan aquí a medida que se crean
    // factory { LoginViewModel(get()) }
}
