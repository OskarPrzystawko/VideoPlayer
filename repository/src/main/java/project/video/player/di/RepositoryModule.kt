package project.video.player.di

import org.koin.dsl.module
import project.video.player.repository.video.VideoApi
import project.video.player.repository.video.VideoRepository
import project.video.player.repository.video.VideoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val VIDEO_API_BASE_URL = "https://api.tvmaze.com/"

val repositoryModule = module {

    single<VideoRepository> { VideoRepositoryImpl(get()) }

    single<VideoApi> { get<Retrofit>().create(VideoApi::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(VIDEO_API_BASE_URL)
            .build()
    }

}