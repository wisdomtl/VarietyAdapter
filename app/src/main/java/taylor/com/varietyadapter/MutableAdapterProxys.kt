package taylor.com.varietyadapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MutableAdapterProxys(
    var proxys: MutableList<VarietyAdapter.AdapterProxy<*, *>> = mutableListOf()
) : VarietyAdapter.AdapterProxys {
    override fun size() = proxys.size

    @Suppress("UNCHECKED_CAST")
    override fun <T, VH : ViewHolder> get(index: Int): VarietyAdapter.AdapterProxy<T, VH> = proxys[index] as VarietyAdapter.AdapterProxy<T, VH>

    override fun <T, VH : ViewHolder> add(proxy: VarietyAdapter.AdapterProxy<T, VH>) {
        proxys.add(proxy)
    }

    override fun <T, VH : ViewHolder> remove(proxy: VarietyAdapter.AdapterProxy<T, VH>) {
        proxys.remove(proxy)
    }

    override fun indexOf(cls: Class<*>): Int {
       return proxys.indexOfFirst { it.type == cls }
    }
}