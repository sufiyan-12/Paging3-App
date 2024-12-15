package com.example.paging3app.retrofit

import com.example.paging3app.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("quotes")
    suspend fun getQuoteList(@Query("page") page: Int): Response<QuoteList>
}