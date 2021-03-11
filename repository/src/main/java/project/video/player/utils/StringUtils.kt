package project.video.player.utils


fun String.httpToHttps(): String {
    val httpPrefix = "http"
    val httpsPrefix = "https"

    if (!startsWith(httpsPrefix) && startsWith(httpPrefix)) {
        val urlWithoutPrefix = this.substring(httpPrefix.length)
        return "$httpsPrefix$urlWithoutPrefix"
    }
    return this
}