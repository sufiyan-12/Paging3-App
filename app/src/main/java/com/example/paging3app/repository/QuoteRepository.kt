package com.example.paging3app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paging3app.paging.QuotePagingSource
import com.example.paging3app.retrofit.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteAPI: QuoteAPI) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(quoteAPI) }
    ).liveData
}