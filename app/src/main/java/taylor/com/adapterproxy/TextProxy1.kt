package test.taylor.com.taylorcode.ui.recyclerview.variety

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.ui.*
import taylor.com.varietyadapter.Diff
import taylor.com.varietyadapter.VarietyAdapter

class TextProxy1 : VarietyAdapter.Proxy<Text, TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            TextView {
                layout_id = "tvName"
                layout_width = match_parent
                layout_height = wrap_content
                textSize = 60f
                gravity = gravity_center
                textColor = "#ff00ff"
            }
        }
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, data: Text, index: Int, action: ((Any?) -> Unit)?) {
        holder.tvName?.apply {
            text = data.text
            onClick = {
                action?.invoke(index)
            }
        }
    }
}

data class Text(
    var text: String,
    var type: Int
) : VarietyAdapter.DataProxyMap, Diff {
    override fun toProxy(): String {
        return when (type) {
            1 -> TextProxy1::class.java.toString()
            2 -> TextProxy2::class.java.toString()
            else -> TextProxy2::class.java.toString()
        }
    }

    override fun hashCode(): Int = text.hashCode()
    override fun diff(other: Any?): Any? {
        return when {
            other !is Text -> null
            this.text != other.text -> {
                "text diff"
            }
            else -> {
                null
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Text)?.text == this.text
    }
}

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.find<TextView>("tvName")
}