package com.example.retrofit.retro.daggerhilt

import com.example.retrofit.retro.domain.CountryApiService
import com.example.retrofit.retro.model.CountryDataRecyleView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(private val apiService: CountryApiService) {
    suspend fun getCountries() = apiService.getCountries()
}

//@Singleton
//class CountryRepository @Inject constructor(private val apiService: CountryApiService) {
//    //    suspend fun getCountries() = apiService.getCountries()
//    suspend fun getCountries(): List<CountryDataRecyleView> {
//        val response = apiService.getCountries()
//        return response.body()?.map { countryData ->
//            CountryDataRecyleView(
//                name = countryData.name.common,
//                official = countryData.name.official,
//                capital = countryData.capital,
//                currencies = countryData.currencies,
//                region = countryData.region,
//                timezones = countryData.timezones,
//                flagUrl = countryData.flags.name
//            )
//        } ?: emptyList()
//    }
//}
