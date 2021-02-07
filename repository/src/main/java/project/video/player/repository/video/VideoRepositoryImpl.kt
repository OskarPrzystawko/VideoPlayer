package project.video.player.repository.video

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import project.video.player.repository.video.model.VideoDto

class VideoRepositoryImpl(
    private val videoApi: VideoApi,
) : VideoRepository {

    override fun getVideos(searchPhrase: String): Observable<List<VideoDto>> {
        return videoApi.getVideoList(searchPhrase)
            .subscribeOn(Schedulers.io())
    }

}