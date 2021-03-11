package project.video.player.repository.video

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import project.video.player.repository.video.model.VideoDto
import project.video.player.utils.httpToHttps

class VideoRepositoryImpl(
    private val videoApi: VideoApi,
) : VideoRepository {

    override fun getVideos(searchPhrase: String): Observable<List<VideoDto>> {
        return videoApi.getVideoList(searchPhrase)
            .subscribeOn(Schedulers.io())
            .map(this::changeImageUrlHttpToHttps)
    }

    private fun changeImageUrlHttpToHttps(videoList: List<VideoDto>): List<VideoDto> {
        videoList.forEach {
            it.show.image?.apply { url = url.httpToHttps() }
        }
        return videoList
    }

}