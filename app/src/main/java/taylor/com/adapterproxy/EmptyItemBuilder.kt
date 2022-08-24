package taylor.com.adapterproxy

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.ui.*
import taylor.com.varietyadapter.VarietyAdapter

/**
 * a [VarietyAdapter.ItemBuilder] which will be used when [RecyclerView] is empty
 */
class EmptyItemBuilder:VarietyAdapter.ItemBuilder<EmptyBean,EmptyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            TextView {
                layout_id = "tvEmpty"
                layout_width = match_parent
                gravity = gravity_center
                layout_height = 200
                textSize = 30f
                textColor ="#3F4658"
            }
        }
        return EmptyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, data: EmptyBean, index: Int, action: ((Any?) -> Unit)?) {
        holder.tv?.text = data.text
    }
}

/**
 * a data represent empty state of [RecyclerView]
 */
data class EmptyBean(val text:String)

class EmptyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
   val tv = itemView.find<TextView>("tvEmpty")
}