package project.video.player.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.video.player.R
import project.video.player.ui.video.list.VideoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, VideoListFragment.newInstance())
                    .commitNow()
        }
    }
}