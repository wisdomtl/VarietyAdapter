package test.taylor.com.taylorcode.ui.recyclerview.variety

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.ui.*
import taylor.com.varietyadapter.VarietyAdapter

class TextItemBuilder2 : VarietyAdapter.ItemBuilder<Text, TextViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            TextView {
                layout_id = "tvName"
                layout_width = match_parent
                layout_height = wrap_content
                textSize = 60f
                gravity = gravity_right
                textColor = "#887654"
            }
        }
        return TextViewHolder2(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder2, data: Text, index: Int, action: ((Any?) -> Unit)?) {
        holder.tvName?.text = data.text
    }
}

class TextViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.find<TextView>("tvName")
}