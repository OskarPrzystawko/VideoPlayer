package project.video.player.repository.video.model

import com.google.gson.annotations.SerializedName

data class VideoDto(

    @SerializedName("show")
    val show: ShowDto,

)
