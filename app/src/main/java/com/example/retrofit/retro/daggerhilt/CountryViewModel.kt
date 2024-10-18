package com.example.retrofit.retro.daggerhilt

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.retro.model.CountryData
//import androidx.lifecycle.liveData
//import com.example.retrofit.retro.model.CountryDisplayData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class CountryViewModel @Inject constructor( private val repository: CountryRepository) : ViewModel()
//{
//
//    val countries = liveData {
//        val retrievedCountries = repository.getCountries()
//        emit(retrievedCountries)
//    }
//}

//@HiltViewModel
//class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {
//
//    // LiveData to hold the list of countries
//    private val _countries = MutableLiveData<List<CountryData>>()
//    val countries: LiveData<List<CountryData>> get() = _countries
//
//    // Function to load countries
//    fun loadCountries() {
//        viewModelScope.launch {
//            val response = countryRepository.getCountries()
//                 if (response.isSuccessful) {
//                    _countries.value = response.body() // Update LiveData with the response
//                }
//        }
//    }
//}

//@HiltViewModel
//class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {
//    private val _countries = MutableLiveData<List<CountryData>>()
//    val countries: LiveData<List<CountryData>> get() = _countries
//
//    private val _countriesDisplay = MutableLiveData<CountryDisplayData>()
//    val countriesDisplay: LiveData<CountryDisplayData> get() = _countriesDisplay
//
//    fun loadCountries() {
//        viewModelScope.launch {
//            val response = countryRepository.getCountries()
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    _countries.value = it
//                    _countriesDisplay.value = mapCountriesToDisplayData(it)
//                }
//            }
//        }
//    }
//
//    private fun mapCountriesToDisplayData(countries: List<CountryData>): CountryDisplayData {
//        val flagNames = countries.joinToString(", ") { it.name.common }
//        val countryNames = countries.joinToString(", ") { it.name.common }
//        val countryOfficial = countries.joinToString(", ") { it.name.official }
//        val countryCapital = countries.joinToString(", ") { it.capital.toString() }
//        val countryCurrencies = countries.joinToString(", ") { it.currencies?.values?.firstOrNull()?.name.toString() }
//        val countryCurrenciesSymbol = countries.joinToString(", ") { it.currencies?.values?.firstOrNull()?.symbol.toString() }
//        val countryCurrenciesCode = countries.joinToString(", ") { it.currencies?.keys?.firstOrNull().toString() }
//        val countryRegion = countries.joinToString(", ") { it.region }
//        val countryTimeZones = countries.joinToString(", ") { it.timezones.toString() }
//
//        return CountryDisplayData(flagNames, countryNames, countryOfficial, countryCapital, countryCurrencies, countryCurrenciesSymbol, countryCurrenciesCode, countryRegion, countryTimeZones)
//    }
//}

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {
    val countryFlagName = MutableLiveData<String>()
    val countryFlagImage = MutableLiveData<String>()
    val countryName = MutableLiveData<String>()
    val countryOfficialName = MutableLiveData<String>()
    val countryCapital = MutableLiveData<String>()
    val countryCurrencies = MutableLiveData<String>()
    val countryCurrenciesSymbol = MutableLiveData<String>()
    val countryCurrenciesCode = MutableLiveData<String>()
    val countryRegion = MutableLiveData<String>()
    val countryTimeZone = MutableLiveData<String>()

    val countryDetails = MediatorLiveData<Unit>().apply {
        addSource(countryFlagName) { value = Unit }
        addSource(countryFlagImage) { value = Unit }
        addSource(countryName) { value = Unit }
        addSource(countryOfficialName) { value = Unit }
        addSource(countryCapital) { value = Unit }
        addSource(countryCurrencies) { value = Unit }
        addSource(countryCurrenciesSymbol) { value = Unit }
        addSource(countryCurrenciesCode) { value = Unit }
        addSource(countryRegion) { value = Unit }
        addSource(countryTimeZone) { value = Unit }
    }

    init {
        fetchCountries()
    }

    // Other LiveData properties
    val recyclerViewVisibility = MutableLiveData<Int>(View.GONE)

    fun fetchCountries() {
        viewModelScope.launch {
            val response = countryRepository.getCountries()
            if (response.isSuccessful) {
                response.body()?.let { mainList ->
                    // Store the fetched list for search filtering
                    _allCountries.value = mainList
                    // Show RecyclerView when countries are fetched
                    recyclerViewVisibility.value = View.VISIBLE
                }
            }
        }
    }

    private val _allCountries = MutableLiveData<List<CountryData>>()
    val allCountries: LiveData<List<CountryData>> get() = _allCountries

    fun filterCountryDetails(query: String) {
        val filteredList = _allCountries.value?.filter {
            it.name.common.equals(query, ignoreCase = true)
        }

        filteredList?.firstOrNull()?.let { country ->
            countryFlagName.postValue(country.name.common)
            countryFlagImage.postValue(country.flags.name)
            countryName.postValue("Country: ${country.name.common}")
            countryOfficialName.postValue("Official: ${country.name.official}")
            countryCapital.postValue("Capital: ${country.capital?.joinToString(", ")}")
            countryCurrencies.postValue("Currency: ${country.currencies?.values?.firstOrNull()?.name}")
            countryCurrenciesSymbol.postValue("Currency Symbol: ${country.currencies?.values?.firstOrNull()?.symbol}")
            countryCurrenciesCode.postValue("Currency Code: ${country.currencies?.keys?.firstOrNull()}")
            countryRegion.postValue("Region: ${country.region}")
            countryTimeZone.postValue("Timezones: ${country.timezones}")
        }
    }
    fun showRecyclerView() {
        recyclerViewVisibility.value = View.VISIBLE
    }

    fun hideRecyclerView() {
        recyclerViewVisibility.value = View.GONE
    }

}


//@HiltViewModel
//class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {
//    val countryFlagName = MutableLiveData<List<String>>()
//    private val nameOfFlag = ArrayList<String>()
//    //val countries: LiveData<List<String>> get() = _countries
//
//    val countryName = MutableLiveData<List<String>>()
//    private val nameOfCountry = ArrayList<String>()
//
//    val countryOfficialName = MutableLiveData<List<String>>()
//    private val officialOfCountry = ArrayList<String>()
//
//    val countryCapital = MutableLiveData<List<String>>()
//    private val capitalOfCountry = ArrayList<String>()
//
//    val countryCurrencies = MutableLiveData<List<String>>()
//    private val currencyOfCountry = ArrayList<String>()
//
//    val countryCurrenciesSymbol = MutableLiveData<List<String>>()
//    private val currencySymbolOfCountry = ArrayList<String>()
//
//    val countryCurrenciesCode = MutableLiveData<List<String>>()
//    private val currencyCodeOfCountry = ArrayList<String>()
//
//    val countryRegion  = MutableLiveData<List<String>>()
//    private val regionOfCountry = ArrayList<String>()
//
//    val countryTimeZone = MutableLiveData<List<String>>()
//    private val timezoneOfCountry = ArrayList<String>()
//
//    init {
//        fetchCountries()
//    }
//
//    private fun fetchCountries() {
//        viewModelScope.launch {
//            val response = countryRepository.getCountries()
//            if (response.isSuccessful) {
//                response.body()?.let { mainList ->
//                    mainList?.forEach{ secondaryList ->
//                        nameOfFlag.add(secondaryList.name.common)
//                        countryFlagName.value=(nameOfFlag)
//
//                        nameOfCountry.add(secondaryList.name.common)
//                        countryName.value=(nameOfCountry)
//
//                        officialOfCountry.add(secondaryList.name.official)
//                        countryOfficialName.value=(officialOfCountry)
//
//                        capitalOfCountry.add(secondaryList.capital.toString())
//                        countryCapital.value=(capitalOfCountry)
//
//                        currencyOfCountry.add(secondaryList.currencies?.values?.firstOrNull()?.name.toString())
//                        countryCurrencies.value=(currencyOfCountry)
//
//                        currencySymbolOfCountry.add(secondaryList.currencies?.values?.firstOrNull()?.symbol.toString())
//                        countryCurrenciesSymbol.value=(currencySymbolOfCountry)
//
//                        currencyCodeOfCountry.add(secondaryList.currencies?.keys?.firstOrNull().toString())
//                        countryCurrenciesCode.value=(currencyCodeOfCountry)
//
//                        regionOfCountry.add(secondaryList.region)
//                        countryRegion.value=(regionOfCountry)
//
//                        timezoneOfCountry.add(secondaryList.timezones.toString())
//                        countryTimeZone.value=(timezoneOfCountry)
//                    }
////                    _countries.value = it.map {
////                        CountryDataRecyleView(
////                            name = secondaryList.name.common,
////                            official = secondaryList.name.official,
////                            capital = secondaryList.capital ?: emptyList(),
////                            currencies = secondaryList.currencies ?: emptyMap(),
////                            region = secondaryList.region,
////                            timezones = secondaryList.timezones ?: emptyList(),
////                            flagUrl = secondaryList.flags.name
////                        )
//
////                    }
//                }
//            }
//        }
//    }
//}


//private val _filteredCountries = MutableLiveData<List<CountryDataRecyleView>>()
//val filteredCountries: LiveData<List<CountryDataRecyleView>> get() = _filteredCountries
//
//private val _exactMatch = MutableLiveData<CountryDataRecyleView?>()
//val exactMatch: LiveData<CountryDataRecyleView?> get() = _exactMatch
//
//fun searchCountry(query: String) {
//    _countries.value?.let {
//        val filteredList = it.filter { country -> country.name.contains(query, ignoreCase = true) }
//        _filteredCountries.value = filteredList
//
//        val exactMatch = filteredList.find { country -> country.name.equals(query, ignoreCase = true) }
//        _exactMatch.value = exactMatch
//    }
//}



