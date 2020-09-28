# VarietyAdapter
Combine different view type into one RecyclerView.Adapter easily

```kotlin

// build Adapter
val varietyAdapter = VarietyAdapter().apply {
    // add Proxy
    addProxy(TextProxy1())
    addProxy(TextProxy2())
    addProxy(ImageProxy())
    // set data list
    dataList =mutableListOf(
        Text("item 1",1),
        Image("#00ff00"),
        Text("item 2",2),
        Text("item 3",1),
        Image("#88ff00"),
        Text("item 4",1),
        Text("item 5",2),
        Text("item 6",2),
        Image("#ffff00"),
        Text("item 7",1),
        Text("item 8",2),
        Image("#098f00"),
        Text("item 9",1)
    ) 
    notifyDataSetChanged()
}

// bind to RecyclerView
rv?.adapter = varietyAdapter
rv?.layoutManager = LinearLayoutManager(this)
```
TextProxy1
```
class TextProxy1 : VarietyAdapter.Proxy<Text, TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            TextView {
                layout_id = "tvName"
                layout_width = match_parent
                layout_height = wrap_content
                textSize = 40f
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
) : VarietyAdapter.DataProxyMap {
    override fun toProxy(): String {
        return when (type) {
            1 -> TextProxy1::class.java.toString()
            2 -> TextProxy2::class.java.toString()
            else -> TextProxy2::class.java.toString()
        }
    }
}

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.find<TextView>("tvName")
}
```
