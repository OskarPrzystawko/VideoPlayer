package project.video.player.di

import org.koin.dsl.module
import project.video.player.repository.VideoRepository
import project.video.player.repository.VideoRepositoryImpl

val repositoryModule = module {

    single<VideoRepository> { VideoRepositoryImpl() }

}