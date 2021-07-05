package test.taylor.com.taylorcode.ui.recyclerview.variety

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.layout_dsl.center_horizontal
import taylor.com.ui.*
import taylor.com.varietyadapter.VarietyAdapter

class TextProxy2 : VarietyAdapter.Proxy<Text, TextViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_width = match_parent
                layout_height = 70
                TextView {
                    layout_id = "tvName"
                    layout_width = match_parent
                    layout_height = wrap_content
                    textSize = 15f
                    gravity = gravity_center
                    center_horizontal  = true
                    textColor = "#887654"
                }

                View {
                    layout_width = match_parent
                    layout_height = 1
                    bottom_toBottomOf = parent_id
                    background_color = "#000000"
                }

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