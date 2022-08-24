package taylor.com.adapterproxy

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import taylor.com.animation_dsl.animSet
import taylor.com.ui.*
import taylor.com.varietyadapter.R
import taylor.com.varietyadapter.VarietyAdapter

class FooterItemBuilder : VarietyAdapter.ItemBuilder<Footer, FooterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            ConstraintLayout {
                layout_width = match_parent
                layout_height = 50

                ImageView {
                    layout_id = "ivLoading"
                    layout_width = 20
                    layout_height = 20
                    scaleType = scale_fit_xy
                    center_vertical = true
                    start_toStartOf = parent_id
                    end_toStartOf = "tvLoading"
                    horizontal_chain_style = packed
                    src = R.drawable.ic_loading
                }.also {
                    animSet {
                        objectAnim {
                            target = it
                            interpolator = LinearInterpolator()
                            rotation = floatArrayOf(0f,360f)
                            repeatCount = ValueAnimator.INFINITE
                            duration = 700
                        }
                    }.start()
                }

                TextView {
                    layout_id = "tvLoading"
                    layout_width = wrap_content
                    layout_height = wrap_content
                    textSize = 14f
                    textColor = "#3F4658"
                    gravity = gravity_center
                    center_vertical = true
                    start_toEndOf = "ivLoading"
                    end_toEndOf = parent_id
                    margin_start = 10
                    horizontal_chain_style = packed
                }
            }
        }
        return FooterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, data: Footer, index: Int, action: ((Any?) -> Unit)?) {
        holder.tvLoading?.text = data.str
    }
}

data class Footer(var str: String)

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvLoading = itemView.find<TextView>("tvLoading")
}

