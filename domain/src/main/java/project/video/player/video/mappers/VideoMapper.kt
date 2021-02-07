package project.video.player.video.mappers

import project.video.player.repository.video.model.VideoDto
import project.video.player.video.model.Video

fun List<VideoDto>.toVideoList(): List<Video> {
    return this.map { it.toVideo() }
}

fun VideoDto.toVideo(): Video {
    return Video(
        this.show.name,
        this.show.genres,
        this.show.image?.url,
    )
}