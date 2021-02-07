package project.video.player.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import project.video.player.ui.video.list.VideoListViewModel

val appModule = module {

    viewModel { VideoListViewModel(get()) }

}