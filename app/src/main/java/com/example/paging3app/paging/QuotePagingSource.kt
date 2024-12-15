package com.example.paging3app.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3app.models.QuoteList
import com.example.paging3app.models.Result
import com.example.paging3app.retrofit.QuoteAPI

class QuotePagingSource(val quoteAPI: QuoteAPI): PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try{
            val position = params.key ?: 1
            val response = quoteAPI.getQuoteList(position)
            val results = if(response.isSuccessful && response.body()!=null) response.body() else null
            if(results != null){
                LoadResult.Page(
                    data = response.body()!!.results,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == response.body()!!.totalPages) null else position + 1
                )
            } else{
                LoadResult.Error(RuntimeException("No Data Found"))
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}