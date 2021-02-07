package project.video.player

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import project.video.player.di.domainModule
import project.video.player.di.repositoryModule
import project.video.player.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    repositoryModule,
                )
            )
        }
    }

}