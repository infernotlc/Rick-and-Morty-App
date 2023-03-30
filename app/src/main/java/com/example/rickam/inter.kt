package com.example.rickam

import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyAPI {

    @GET("location")
    fun getLocations(): Call<LocationResponse>
}
