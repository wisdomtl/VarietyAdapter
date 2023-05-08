package taylor.com.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import taylor.com.adapterproxy.*
import taylor.com.varietyadapter.VarietyAdapter
import test.taylor.com.taylorcode.ui.recyclerview.variety.Text
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextItemBuilder1
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextItemBuilder2
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextViewHolder

class VarietyAdapterActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private var itemNumber: Int = 1

    private var rv: RecyclerView? = null

    private val varietyAdapter = VarietyAdapter().apply {
        addItemBuilder(TextItemBuilder1())
        addItemBuilder(TextItemBuilder2())
        addItemBuilder(ImageItemBuilder())
        addItemBuilder(EmptyItemBuilder())
        addItemBuilder(FooterItemBuilder())

        // add default content for RecyclerView
        dataList = listOf(
            Text(itemNumber, "item ${itemNumber++}", 2),
//            Image("#00ff00"),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
//            Image("#88ff00"),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
//            Image("#ffff00"),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
//            Image("#098f00"),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
            Text(itemNumber, "item ${itemNumber++}", 2),
//            Footer("loading")
        )
        preloadItemCount = 2
        // define preload action
        onPreload = {
            // append new data to the tail of existing data
            val oldList = dataList
            // as if data comes from server
            Log.v("ttaylor", "tag=asdf, VarietyAdapterActivity.()  onPreLoad")
            launch(Dispatchers.IO) {
                val newDataList = oldList.toMutableList().apply {
                    val footer = removeAt(size - 1)
                    addAll(
                        listOf(
                            Text(itemCount, "item ${itemNumber++}"),
                            Text(itemCount, "item ${itemNumber++}"),
                            Text(itemCount, "item ${itemNumber++}"),
                            Text(itemCount, "item ${itemNumber++}"),
                            Text(itemCount, "item ${itemNumber++}"),
                            footer
                        )
                    )

                }
                delay(2000)
                withContext(Dispatchers.Main) {
                    dataList = newDataList
                }
            }
        }

        onViewDetachedFromWindow = {
            val viewHolder = (it as? TextViewHolder)
            Log.v("ttaylor", "tag=, onViewDetachedFromWindow.()  text=${viewHolder?.tvName?.text}")
        }

        onViewAttachedToWindow = {
            val viewHolder = (it as? TextViewHolder)
            Log.v("ttaylor", "tag=, onViewAttachedToWindow.()  text=${viewHolder?.tvName?.text}")
        }
    }

    private val rootView by lazy {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent

            Button {
                layout_id = "tvEmpty"
                layout_width = wrap_content
                layout_height = wrap_content
                textSize = 16f
                padding = 5
                textColor = "#3F4658"
                gravity = gravity_center
                text = "empty list"
                top_toTopOf = parent_id
                start_toStartOf = parent_id
                onClick = {
                    // demonstrate how to show empty view in VarietyAdapter
                    varietyAdapter.apply {
                        dataList = listOf(
                            EmptyBean("No items in the list!")
                        )
                    }
                }
            }

            Button {
                layout_id = "btnRemove"
                layout_width = wrap_content
                layout_height = wrap_content
                textSize = 16f
                padding = 5
                textColor = "#3F4658"
                gravity = gravity_center
                text = "remove"
                start_toEndOf = "tvEmpty"
                align_vertical_to = "tvEmpty"
                onClick = {
                    val newList = varietyAdapter.dataList.toMutableList().apply { removeAt(1) }
                    varietyAdapter.dataList = newList
                }
            }

            Button {
                layout_id = "btnPartUpdate"
                layout_width = wrap_content
                layout_height = wrap_content
                textSize = 16f
                padding = 5
                gravity = gravity_center
                text = "partially update"
                start_toEndOf = "btnRemove"
                align_vertical_to = "btnRemove"
                onClick = {
                    val oldData = varietyAdapter.dataList.toMutableList()
                    val first = oldData.firstOrNull() as? Text
                    if (first != null) {
                        val newFirst = first.copy().apply {
                            text = "100"
                        }
                        oldData.set(0, newFirst)
                        varietyAdapter.dataList = oldData
                    }
                }
            }

            rv = RecyclerView {
                layout_width = match_parent
                layout_height = 0
                top_toBottomOf = "tvEmpty"
                bottom_toBottomOf = parent_id
                center_horizontal = true
                adapter = varietyAdapter
                layoutManager = LinearLayoutManager(this@VarietyAdapterActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView)
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