package project.video.player.repository.video

import io.reactivex.rxjava3.core.Observable
import project.video.player.repository.video.model.VideoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {

    @GET("/search/shows")
    fun getVideoList(@Query("q") searchPhrase: String): Observable<List<VideoDto>>

}