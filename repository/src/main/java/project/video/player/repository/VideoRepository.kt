package project.video.player.repository

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.model.VideoDto

interface VideoRepository {

    fun getVideos(): Observable<List<VideoDto>>

}