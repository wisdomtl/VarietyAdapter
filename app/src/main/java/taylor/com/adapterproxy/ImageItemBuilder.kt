package taylor.com.adapterproxy

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.ui.*
import taylor.com.varietyadapter.VarietyAdapter

class ImageItemBuilder : VarietyAdapter.ItemBuilder<Image, ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            ImageView {
                layout_id = "ivAvatar"
                layout_width = match_parent
                layout_height = 90
                scaleType = scale_fit_xy
            }
        }

        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, data: Image, index: Int, action: ((Any?) -> Unit)?) {
        holder.ivAvatar?.background_color = data.color
    }
}

data class Image(var color: String)

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivAvatar = itemView.find<ImageView>("ivAvatar")
}