package project.video.player.di

import org.koin.dsl.module
import project.video.player.interactors.GetVideosUseCase

val domainModule = module {

    factory { GetVideosUseCase(get()) }

}