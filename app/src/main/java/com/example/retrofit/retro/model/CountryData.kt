package com.example.retrofit.retro.model

import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("name")
    val name: Name,

    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("timezones")
    val timezones:  List<String>?,

    @SerializedName("region")
    val region: String,

    @SerializedName("currencies")
    val currencies: Map<String, Currency>?,

    @SerializedName("flags")
    val flags: Flag
)

data class Name(
    @SerializedName("common")
    val common: String,

    @SerializedName("official")
    val official: String,

    @SerializedName("nativeName")
    val nativeName: NativeName
)

data class NativeName(
    @SerializedName("eng")
    val eng: Eng
)

data class Eng(
    @SerializedName("official")
    val official: String,

    @SerializedName("common")
    val common: String
)

data class Flag(
    @SerializedName("png")
    val name: String
)

data class CountryDataRecyleView(
    val name: String,
    val official: String,
    val capital: List<String>?,
    val currencies: Map<String, Currency>?,
    val region: String,
    val timezones: List<String>?,
    val flagUrl: String
)

data class Currency(
    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String
)

data class CurrencyRecyleView(
    val name: String,
    val symbol: String
)

//data class CountryDisplayData(
//    val flagNames: String,
//    val countryNames: String,
//    val countryOfficial: String,
//    val countryCapital: String,
//    val countryCurrencies: String,
//    val countryCurrenciesSymbol: String,
//    val countryCurrenciesCode: String,
//    val countryRegion: String,
//    val countryTimeZones: String
//)

//data class CountryDisplayData(
//    val name: String,
//    val official: String,
//    val capital: List<String>?,
//    val currencies: Map<String, Currency>?,
//    val region: String,
//    val timezones: List<String>?,
//    val flagUrl: String
//)









