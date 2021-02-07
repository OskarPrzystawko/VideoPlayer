package project.video.player.repository.video

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.video.model.VideoDto

interface VideoRepository {

    fun getVideos(searchPhrase: String): Observable<List<VideoDto>>

}