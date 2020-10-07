package taylor.com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taylor.com.adapterproxy.Image
import taylor.com.adapterproxy.ImageProxy
import taylor.com.varietyadapter.VarietyAdapter
import test.taylor.com.taylorcode.ui.recyclerview.variety.Text
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextProxy1
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextProxy2

class VarietyAdapterActivity : AppCompatActivity() {

    private var rv: RecyclerView? = null

    private var itemNumber: Int = 1

    private lateinit var datas: MutableList<Any>

    private val rootView by lazy {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent

            rv = RecyclerView {
                layout_width = match_parent
                layout_height = wrap_content
                top_toTopOf = parent_id
                center_horizontal = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView)

        val varietyAdapter = VarietyAdapter().apply {
            addProxy(TextProxy1())
            addProxy(TextProxy2())
            addProxy(ImageProxy())
            dataList = listOf(
                Text("item ${itemNumber++}", 1),
                Image("#00ff00"),
                Text("item ${itemNumber++}", 2),
                Text("item ${itemNumber++}", 1),
                Image("#88ff00"),
                Text("item ${itemNumber++}", 1),
                Text("item ${itemNumber++}", 2),
                Text("item ${itemNumber++}", 2),
                Image("#ffff00"),
                Text("item ${itemNumber++}", 1),
                Text("item ${itemNumber++}", 2),
                Image("#098f00"),
                Text("item ${itemNumber++}", 1)
            )
            preloadItemCount = 2
            onPreload = {
                // append new data to the tail of existing data
                val oldList = dataList
                dataList = oldList.toMutableList().apply {
                    addAll(
                        listOf(
                            Text("item ${itemNumber++}", 2),
                            Text("item ${itemNumber++}", 2),
                            Text("item ${itemNumber++}", 2),
                            Text("item ${itemNumber++}", 2),
                            Text("item ${itemNumber++}", 2),
                        )
                    )
                }
            }
        }

        rv?.adapter = varietyAdapter
        rv?.layoutManager = LinearLayoutManager(this)
    }
}

fun RecyclerView.addTopBottomListener(onBorder: (direction: Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy != 0) {
                if (!canScrollVertically(-1)) {
                    onBorder(-1)
                } else if (!canScrollVertically(1)) {
                    onBorder(1)
                }
            }
        }
    })
}