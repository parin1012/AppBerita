package com.udacoding.appberita.network

import retrofit2.Call
import retrofit2.http.GET
import com.udacoding.appberita.model.ResponseServer

interface BeritaService {
    @GET("everything?q=bitcoin&from=2020-08-25&sortBy=publishedAt&apiKey=265eb491c55845caac93312688e598d5")
    fun getDataBerita(): Call<ResponseServer>
}