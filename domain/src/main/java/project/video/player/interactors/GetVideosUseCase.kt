package project.video.player.interactors

import project.video.player.repository.VideoRepository

class GetVideosUseCase(
    private val videoRepository: VideoRepository,
) {

    operator fun invoke() = videoRepository.getVideos()

}