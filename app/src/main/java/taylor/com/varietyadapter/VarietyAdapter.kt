package taylor.com.varietyadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import taylor.com.varietyadapter.VarietyAdapter.AdapterProxy
import java.lang.reflect.ParameterizedType

/**
 * A special [RecyclerView.Adapter] which could show variety types of item without rewrite [onCreateViewHolder], [onBindViewHolder] and [getItemViewType].
 * New type of item is added dynamically by [addProxy].
 *
 * the typical usage is like the following:
 *
 * val varietyAdapter = VarietyAdapter().apply {
 *      addProxy(TextAdapterProxy()) // add a new type of item dynamically
 *      addProxy(ImageAdapterProxy()) // add anther new type of item dynamically
 * }
 *
 * // two different types of data
 * val text = TextBean("item 1") // a string data
 * val image = ImageBean("https://xxx.png") // a image url data
 *
 * // combine different type of data into one list
 * val data = mutableListOf<Any>()
 * for (i in 1..10){
 *      data.add(text)
 *      data.add(image)
 * }
 *
 * // bind data to adapter
 * varietyAdapter.datas = data
 *
 * // bind adapter to RecyclerView
 * recyclerView.adapter = varietyAdapter
 * recyclerView.layoutManager = LinearLayoutManager(context)
 * varietyAdapter.notifyDataSetChanged()
 */
class VarietyAdapter(
    /**
     * the list of [AdapterProxy]
     */
    private var proxyList: MutableList<AdapterProxy<*, *>> = mutableListOf(),
    /**
     * the data of this adapter
     */
    var dataList: MutableList<Any> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {

    /**
     * add a new type of item for RecyclerView
     */
    fun <T, VH : ViewHolder> addProxy(proxy: AdapterProxy<T, VH>) {
        proxyList.add(proxy)
    }

    /**
     * remove a type of item for RecyclerView
     */
    fun <T, VH : ViewHolder> removeProxy(proxy: AdapterProxy<T, VH>) {
        proxyList.remove(proxy)
    }

    /**
     * a way for [ViewHolder] to communicate with [RecyclerView.Adapter]
     */
    var action: ((Any?) -> Unit)? = null

    var onAttachedToRecyclerView: ((recyclerView: RecyclerView) -> Unit)? = null
    var onDetachedFromRecyclerView: ((recyclerView: RecyclerView) -> Unit)? = null
    var onFailedToRecycleView: ((holder: ViewHolder) -> Boolean)? = null
    var onViewAttachedToWindow: ((holder: ViewHolder) -> Unit)? = null
    var onViewDetachedFromWindow: ((holder: ViewHolder) -> Unit)? = null
    var onViewRecycled: ((holder: ViewHolder) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return proxyList[viewType].onCreateViewHolder(parent, viewType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (proxyList[getItemViewType(position)] as AdapterProxy<Any, ViewHolder>).onBindViewHolder(holder, dataList[position], position, action)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        (proxyList[getItemViewType(position)] as AdapterProxy<Any, ViewHolder>).onBindViewHolder(
            holder,
            dataList[position],
            position,
            action,
            payloads
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return getProxyIndex(dataList[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        onAttachedToRecyclerView?.invoke(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        onDetachedFromRecyclerView?.invoke(recyclerView)
    }

    override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
        return onFailedToRecycleView?.invoke(holder) ?: return super.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        onViewAttachedToWindow?.invoke(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        onViewDetachedFromWindow?.invoke(holder)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        onViewRecycled?.invoke(holder)
    }

    private fun getProxyIndex(data: Any): Int = proxyList.indexOfFirst {
        (it.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString() == data.javaClass.toString()
    }

    /**
     * the proxy of [RecyclerView.Adapter], which has the similar function to it.
     * the business layer implements [AdapterProxy] to define how does the item look like
     */
    abstract class AdapterProxy<T, VH : ViewHolder> {

        abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

        abstract fun onBindViewHolder(holder: VH, data: T, index: Int, action: ((Any?) -> Unit)? = null)

        open fun onBindViewHolder(holder: VH, data: T, index: Int, action: ((Any?) -> Unit)? = null, payloads: MutableList<Any>) {
            onBindViewHolder(holder, data, index, action)
        }
    }
}