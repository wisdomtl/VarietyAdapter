package taylor.com.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import test.taylor.com.taylorcode.ui.recyclerview.variety.Text

class MyPagingSource(private val repo:TextRepository):PagingSource<Int,Text>() {
    override fun getRefreshKey(state: PagingState<Int, Text>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Text> {
        Log.d("ttaylor", "MyPagingSource.load(param=$params) ")
        return  try {
            val key = params.key ?: 0
            val loadSize = params.loadSize ?: 10
            delay(500)
            val data = repo.getText(loadSize)
            LoadResult.Page(
                data,
                if(key <=0) null else key-1,
                if(data.isNullOrEmpty()) null else key+1
            )
        } catch(exception:java.lang.Exception){
            LoadResult.Error(exception)
        }
    }
}