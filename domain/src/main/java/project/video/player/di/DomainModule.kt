package project.video.player.di

import org.koin.dsl.module
import project.video.player.video.interactors.GetVideoListUseCase

val domainModule = module {

    factory { GetVideoListUseCase(get()) }

}