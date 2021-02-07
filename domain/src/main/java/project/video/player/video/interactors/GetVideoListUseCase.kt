package project.video.player.video.interactors

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.video.VideoRepository
import project.video.player.video.mappers.toVideoList
import project.video.player.video.model.Video

class GetVideoListUseCase(
    private val videoRepository: VideoRepository,
) {

    operator fun invoke(searchPhrase: String): Observable<List<Video>> =
        videoRepository.getVideos(searchPhrase)
            .map {
                it.toVideoList()
            }

}