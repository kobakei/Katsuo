package io.github.kobakei.katsuo

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.kobakei.katsuo.api.apiClient
import io.github.kobakei.katsuo.author.AuthorRouterImpl
import io.github.kobakei.katsuo.author.AuthorViewModel
import io.github.kobakei.katsuo.database.ArticleDao
import io.github.kobakei.katsuo.database.createDb
import io.github.kobakei.katsuo.detail.DetailRouterImpl
import io.github.kobakei.katsuo.detail.DetailViewModel
import io.github.kobakei.katsuo.repository.AdRepository
import io.github.kobakei.katsuo.repository.ArticleRepository
import io.github.kobakei.katsuo.router.AuthorRouter
import io.github.kobakei.katsuo.router.DetailRouter
import io.github.kobakei.katsuo.router.Router
import io.github.kobakei.katsuo.timeline.TimelineViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                routerModule,
                viewModelModule,
                repoModule,
                dataModule
            )
        }
    }
}

val dataModule = module {
    single { apiClient() }
    single { createDb(get()).articleDao() }
}

val repoModule = module {
    single { ArticleRepository(get(), get()) }
    single { AdRepository(get()) }
}

val routerModule = module {
    single<DetailRouter> { DetailRouterImpl() }
    single<AuthorRouter> { AuthorRouterImpl() }
    single { Router(get(), get()) }
}

val viewModelModule = module {
    viewModel { TimelineViewModel(get(), get()) }
    viewModel { DetailViewModel() }
    viewModel { AuthorViewModel(get()) }
}