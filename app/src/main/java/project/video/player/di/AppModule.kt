package project.video.player.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import project.video.player.ui.video.list.VideoListViewModel

const val VIDEO_SEARCH_PHRASE_MIN_LENGTH = 3
const val VIDEO_SEARCH_DEBOUNCE_TIME_MS = 600L

val appModule = module {

    viewModel { VideoListViewModel(get()) }

}