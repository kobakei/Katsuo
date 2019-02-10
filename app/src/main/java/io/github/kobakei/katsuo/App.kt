package io.github.kobakei.katsuo

import android.app.Application
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
        }

        val module = module {
            viewModel { TimelineViewModel() }
        }
        startKoin(this, listOf(module))
    }

}