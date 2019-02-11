package io.github.kobakei.katsuo

import android.app.Application
import com.facebook.stetho.Stetho
import io.github.kobakei.katsuo.author.AuthorRouterImpl
import io.github.kobakei.katsuo.author.AuthorViewModel
import io.github.kobakei.katsuo.detail.DetailRouterImpl
import io.github.kobakei.katsuo.detail.DetailViewModel
import io.github.kobakei.katsuo.repository.AdRepository
import io.github.kobakei.katsuo.repository.ArticleRepository
import io.github.kobakei.katsuo.router.AuthorRouter
import io.github.kobakei.katsuo.router.DetailRouter
import io.github.kobakei.katsuo.router.Router
import io.github.kobakei.katsuo.timeline.TimelineViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        startKoin(this, listOf(
            routerModule,
            viewModelModule,
            repoModule
        ))
    }

}

val repoModule = module {
    single { ArticleRepository(get()) }
    single { AdRepository() }
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