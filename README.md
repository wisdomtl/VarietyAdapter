# VarietyAdapter
Combine different view type into one RecyclerView.Adapter easily

```kotlin
// build Adapter
val varietyAdapter = VarietyAdapter().apply {
    // add Proxy
    addProxy(TextProxy1()) // firsr item type
    addProxy(ImageProxy()) // second item type
    // set data list， no need to notify data set change, it is done by DiffUtil which is inside VarietyAdapter
    dataList =mutableListOf(
        Text("item 1",1),
        Image("#00ff00"),
        Text("item 2",2),
        Text("item 3",1),
        Image("#88ff00")
    )
}

// bind to RecyclerView
rv?.adapter = varietyAdapter
rv?.layoutManager = LinearLayoutManager(this)
```
Proxy is the place to define how item looks like
```kotlin
class TextProxy1 : VarietyAdapter.Proxy<Text, TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // building item view by layout dsl
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

    // binding data to item view
    override fun onBindViewHolder(holder: TextViewHolder, data: Text, index: Int, action: ((Any?) -> Unit)?) {
        holder.tvName?.apply { text = data.text }
    }
}

data class Text(
    var text: String,
    var type: Int
)

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.find<TextView>("tvName")
}
```
