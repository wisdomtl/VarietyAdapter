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
