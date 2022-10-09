package taylor.com.paging3

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import taylor.com.ui.*
import test.taylor.com.taylorcode.ui.recyclerview.variety.Text
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextViewHolder2

class PagingAdapter:PagingDataAdapter<Text,TextViewHolder2>(ItemCallback2()) {
    override fun onBindViewHolder(holder: TextViewHolder2, position: Int) {
        val data = getItem(position)
        holder.tvName?.text = data?.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder2 {
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
}