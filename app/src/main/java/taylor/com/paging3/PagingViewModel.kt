package taylor.com.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PagingViewModel(repository:TextRepository):ViewModel() {

    val pagingData = Pager(
        config = PagingConfig(pageSize = 15, enablePlaceholders = false, initialLoadSize = 30, prefetchDistance = 1),
        pagingSourceFactory = {MyPagingSource(repository)}
    ).flow.cachedIn(viewModelScope)
}