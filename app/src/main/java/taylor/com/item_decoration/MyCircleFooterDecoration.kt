package taylor.com.item_decoration

import android.graphics.Canvas
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taylor.com.layout_dsl.*

/**
 * draw a footer at the end of horizontal RecyclerView
 */
class MyCircleFooterDecoration : RecyclerView.ItemDecoration() {

    private var footerView: View? = null

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        footerView ?: kotlin.run {
            footerView = parent.context?.run {
                ConstraintLayout {
                    layout_width = 18.5f
                    layout_height = 76
                    shape = shape {
                        corner_radii = intArrayOf(5, 5, 0, 0, 0, 0, 5, 5)
                        solid_color = "#ECEEF1"
                    }

                    TextView {
                        layout_width = 11.5f
                        layout_height = wrap_content
                        center_vertical = true
                        end_toEndOf = parent_id
                        textSize = 10f
                        textColor = "#9098A2"
                        margin_end =1
                        text = "more"
                    }
                }
            }
        }

        prepareFooter(parent, footerView)
        drawFooter(parent,c,footerView)
        Log.v(
            "ttaylor",
            "tag=, MyItemDecoration.onDrawOver()  remainingScrollVertical=${state.remainingScrollVertical} , targetScrollPosition=${state.targetScrollPosition},hasTargetScrollPosition = ${state.hasTargetScrollPosition()}"
        )
    }

    private fun drawFooter(parent:RecyclerView,c: Canvas, footerView: View?) {
        footerView ?: return
        c.save()
        val dx = parent.measuredWidth - footerView.measuredWidth
        c.translate(dx.toFloat(),0f)
        footerView.draw(c)
        c.restore()
    }


    private fun prepareFooter(parent: ViewGroup, view: View?) {
        view ?: return

        // Specs for parent (RecyclerView)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        // Specs for children (headers)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, parent.paddingLeft + parent.paddingRight, view.layoutParams.width)
        val childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, parent.paddingTop + parent.paddingBottom, view.layoutParams.height)
        view.measure(childWidthSpec, childHeightSpec)

        val left = parent.measuredWidth - view.measuredWidth
        val right = parent.measuredWidth
        val top = 0
        val bottom = view.measuredHeight
        view.layout(left, top, right, bottom)
    }
}
