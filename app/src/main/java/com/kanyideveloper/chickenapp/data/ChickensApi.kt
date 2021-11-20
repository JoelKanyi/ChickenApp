package com.kanyideveloper.chickenapp.data

import retrofit2.http.GET

interface ChickensApi {

    companion object{
        const val BASE_URL = "http://192.168.43.76:8080"
    }

    @GET("/chickens")
    suspend fun getChickens() : List<Chicken>
}