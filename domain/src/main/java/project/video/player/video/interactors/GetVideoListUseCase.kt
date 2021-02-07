package project.video.player.video.interactors

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.VideoRepository
import project.video.player.video.mappers.toVideoList
import project.video.player.video.model.Video

class GetVideoListUseCase(
    private val videoRepository: VideoRepository,
) {

    operator fun invoke(): Observable<List<Video>> = videoRepository.getVideos()
        .map {
            it.toVideoList()
        }

}