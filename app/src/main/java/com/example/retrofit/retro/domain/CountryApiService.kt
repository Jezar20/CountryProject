package com.example.retrofit.retro.domain

import com.example.retrofit.retro.model.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {
    @GET("/v3.1/all")
    suspend fun getCountries(): Response<Countries>
}