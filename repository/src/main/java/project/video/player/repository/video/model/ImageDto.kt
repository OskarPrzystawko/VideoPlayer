package project.video.player.repository.video.model

import com.google.gson.annotations.SerializedName

data class ImageDto(

    @SerializedName("medium")
    val url: String,

)