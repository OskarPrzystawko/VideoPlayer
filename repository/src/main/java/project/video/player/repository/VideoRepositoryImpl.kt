package project.video.player.repository

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.model.VideoDto

class VideoRepositoryImpl : VideoRepository {

    override fun getVideos(): Observable<List<VideoDto>> {
        TODO("Not yet implemented")
    }
}