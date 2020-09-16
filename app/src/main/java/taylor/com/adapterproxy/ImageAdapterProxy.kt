package taylor.com.adapterproxy

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.varietyadapter.VarietyAdapter
import taylor.com.ui.*
import taylor.com.varietyadapter.R

class ImageAdapterProxy: VarietyAdapter.AdapterProxy<Image, ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            ImageView {
                layout_id = "ivAvatar"
                layout_width = match_parent
                layout_height = 50
                scaleType = scale_fix_xy
                background_res = R.drawable.ic_launcher_background
            }
        }

        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, data: Image, index: Int, action: ((Any?) -> Unit)?) {

    }
}


data class Image(
    var url:String
)

class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val ivAvatar = itemView.find<ImageView>("ivAvatar")
}