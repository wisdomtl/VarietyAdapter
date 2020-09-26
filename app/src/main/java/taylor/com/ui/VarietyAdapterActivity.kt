package taylor.com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taylor.com.adapterproxy.Image
import taylor.com.adapterproxy.ImageProxy
import taylor.com.varietyadapter.VarietyAdapter
import test.taylor.com.taylorcode.ui.recyclerview.variety.Text
import test.taylor.com.taylorcode.ui.recyclerview.variety.TextProxy

class VarietyAdapterActivity : AppCompatActivity() {

    private var rv: RecyclerView? = null

    private var lastTextIndex: Int = -1

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
            addProxy(TextProxy())
            addProxy(ImageProxy())
        }

        datas = mutableListOf(
            Text("item 1"),
            Image("#00ff00"),
            Text("item 2"),
            Text("item 3"),
            Image("#88ff00"),
            Text("item 4"),
            Text("item 5"),
            Text("item 6"),
            Image("#ffff00"),
            Text("item 7"),
            Text("item 8"),
            Image("#098f00"),
            Text("item 9")
        )
        varietyAdapter.dataList = datas

        rv?.adapter = varietyAdapter
        rv?.layoutManager = LinearLayoutManager(this)
        varietyAdapter.notifyDataSetChanged()
    }
}