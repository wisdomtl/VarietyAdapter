package test.taylor.com.taylorcode.ui.recyclerview.variety

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.ui.*
import taylor.com.varietyadapter.Diff
import taylor.com.varietyadapter.VarietyAdapter

class TextItemBuilder1 : VarietyAdapter.ItemBuilder<Text, TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_width = match_parent
                layout_height = 40

                TextView {
                    layout_id = "tvName"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 30f
                    gravity = gravity_center
                    textColor = "#ff00ff"
                    end_toEndOf = parent_id
                    center_vertical = true
                }

                View {
                    layout_id = "v"
                    layout_width = 40
                    layout_height = 40
                    start_toStartOf = parent_id
                    center_vertical = true
                }
            }

        }
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, data: Text, index: Int, action: ((Any?) -> Unit)?, payloads: MutableList<Any>) {
        Log.v("ttaylor", "tag=, TextProxy1.onBindViewHolder()  partially payload.size=${payloads.size}")
        val payload = payloads.firstOrNull()
        payload ?: kotlin.run {
            super.onBindViewHolder(holder, data, index, action, payloads)
            return
        }
        holder.tvName?.apply {
            text = data.text
            onClick = {
                action?.invoke(index)
            }
        }
    }

    override fun onBindViewHolder(holder: TextViewHolder, data: Text, index: Int, action: ((Any?) -> Unit)?) {
        holder.tvName?.apply {
            text = data.text
            onClick = {
                action?.invoke(index)
            }
        }
        holder.v?.shape = shape {
            solid_color = "#ff00ff"
            corner_radius = 15
        }
    }
}

data class Text(
    var id: Int,
    var text: String,
    var type: Int = 2
) : VarietyAdapter.DataProxyMap, Diff {
    override fun toProxy(): String {
        return when (type) {
            1 -> TextItemBuilder1::class.java.toString()
            2 -> TextItemBuilder2::class.java.toString()
            else -> TextItemBuilder2::class.java.toString()
        }
    }

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

    override fun sameAs(other: Any?): Boolean {
        return when {
            other !is Text -> false
            this.id != other.id -> false
            else -> true
        }
    }

    override fun contentSameAs(other: Any?): Boolean {
        return when {
            other !is Text -> false
            this.text != other.text -> false
            else -> true
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other as? Text)?.text == this.text
    }

    fun copy() = Text(id, text, type)
}

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.find<TextView>("tvName")
    val v = itemView.find<View>("v")
}