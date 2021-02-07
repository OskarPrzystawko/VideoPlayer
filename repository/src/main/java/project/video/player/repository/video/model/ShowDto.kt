package project.video.player.repository.video.model

import com.google.gson.annotations.SerializedName

data class ShowDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("genres")
    val genres: List<String>,

    @SerializedName("image")
    val image: ImageDto?,

)