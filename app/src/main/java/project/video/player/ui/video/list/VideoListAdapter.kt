package project.video.player.ui.video.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.video.player.R
import project.video.player.utils.ImageLoaderUtils.loadImage
import project.video.player.video.model.Video

class VideoListAdapter: RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    var data = listOf<Video>()
        set(value) {
            if (data.isNullOrEmpty() && value.isNullOrEmpty()) return
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.video_title)
        private val genres: TextView = itemView.findViewById(R.id.video_genres)
        private val videoImage: ImageView = itemView.findViewById(R.id.video_icon)

        fun bind(item: Video) {
            title.text = item.name
            genres.text = item.genres.joinToString()
            videoImage.loadImage(item.imageUrl, android.R.drawable.ic_delete)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_list_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}