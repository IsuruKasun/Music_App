package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIinterface {

    @Headers("X-RapidAPI-Key: 6a6c2a4652msh061d230e1af8c83p1d50bajsn7c68fbc92a8d",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>

}