package com.example.retrofit.retro.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.retro.CountryDetailsView
import com.example.retrofit.retro.daggerhilt.CountryViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        responseLiveData.observe(this, Observer { response ->
//            val countryList = response.body()
//            setupSearchView(countryList)
//        })
//    }
//
//    private fun setupSearchView(countryList: List<CountryData>?) {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let { searchCountry(it, countryList) }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { searchCountry(it, countryList) }
//                return true
//            }
//        })
//    }
//
//    private fun searchCountry(query: String, countryList: List<CountryData>?) {
//        countryList?.let {
//            val countryData = it.find { country -> country.name.common.equals(query, ignoreCase = true) }
//            countryData?.let { displayCountryDetails(it) }
//        }
//    }
//
//    private fun displayCountryDetails(countryData: CountryData) {
//        binding.flagNameTxt.text = countryData.name.common
//
//        binding.nameTxt.text = "Country: " + countryData.name.common
//        binding.officialTxt.text = "Official: " + countryData.name.official
//        binding.capitalTxt.text = "Capital: " + countryData.capital?.joinToString(", ")
//
//        countryData.currencies?.forEach { (currencyCode, currency) ->
//            //binding.currenciesTxt.text = "Currency: (" + currency.symbol + ") (" + currencyCode + ") " + currency.name
//            binding.currenciesTxt.text = "Currency: " + currency.name
//            binding.currenciesSymbolTxt.text = "Currency Symbol: " + currency.symbol
//            binding.currenciesCodeTxt.text = "Currency Code: " + currencyCode
//        }
//
//        binding.regionTxt.text = "Region: " + countryData.region
//        binding.timezonesTxt.text = "Timezones: " + countryData.timezones?.joinToString(", ")
//
//        Picasso.get()
//            .load(countryData.flags.name)
//            .into(binding.flagImageView)
//    }
//}

//WORKING ADAPTER

//class MainActivity : AppCompatActivity() {
//    lateinit var countryListRv: ListView
//    lateinit var listAdapter: ArrayAdapter<String>
//    lateinit var countryList: ArrayList<String>
//    lateinit var searchView: SearchView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        countryListRv = findViewById(R.id.countryListTxt)
//        searchView = findViewById(R.id.searchView)
//        countryList = ArrayList()
//
//        // Initialize the adapter with the empty list
//        listAdapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            countryList
//        )
//        countryListRv.adapter = listAdapter
//
//        // Fetch country names from API and add to the list
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        responseLiveData.observe(this, Observer { response ->
//            val countries = response.body()
//            countries?.forEach { countryData ->
//                countryList.add(countryData.name.common)
//            }
//            listAdapter.notifyDataSetChanged()
//        })
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                listAdapter.filter.filter(newText)
//                return false
//            }
//        })
//    }
//}

// WORKING OVERALL USING ADAPTER NOT RECYCLEVIEW

//class MainActivity : AppCompatActivity() {
//    lateinit var countryListRv: ListView
//    lateinit var listAdapter: ArrayAdapter<String>
//    lateinit var countryList: ArrayList<String>
//    lateinit var searchView: SearchView
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        countryListRv = findViewById(R.id.countryListTxt)
//        searchView = findViewById(R.id.searchView)
//        countryList = ArrayList()
//
//        // Initialize the adapter with the empty list
//        listAdapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            countryList
//        )
//        countryListRv.adapter = listAdapter
//
//        // Set initial visibility of the ListView to GONE
//        countryListRv.visibility = View.GONE
//
//        // Fetch country names from API and add to the list
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        responseLiveData.observe(this, Observer { response ->
//            val countries = response.body()
//            countries?.forEach { countryData ->
//                countryList.add(countryData.name.common)
//            }
//            listAdapter.notifyDataSetChanged()
//            setupSearchView(countries)
//        })
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                listAdapter.filter.filter(newText)
//                return false
//            }
//        })
//        // Show adapter when search bar is clicked
//        searchView.setOnSearchClickListener {
//            countryListRv.visibility = View.VISIBLE
//        }
//
//        searchView.setOnCloseListener {
//            countryListRv.visibility = View.GONE
//            false
//        }
//    }
//
//    private fun setupSearchView(countryList: List<CountryData>?) {
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchCountry(query ?: "", countryList)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                listAdapter.filter.filter(newText)
//                if (newText.isNullOrEmpty()) {
//                    countryListRv.visibility = View.GONE
//                } else {
//                    countryListRv.visibility = View.VISIBLE
//                }
//                return false
//            }
//        })
//        searchView.setOnSearchClickListener {
//            countryListRv.visibility = View.VISIBLE
//        }
//        countryListRv.setOnItemClickListener { parent, view, position, id ->
//            val selectedCountryName = listAdapter.getItem(position) as String
//            val selectedCountryData = countryList?.find { it.name.common == selectedCountryName }
//            selectedCountryData?.let { displayCountryDetails(it) }
//            countryListRv.visibility = View.GONE
//        }
//    }
//
//    private fun searchCountry(query: String, countryList: List<CountryData>?) {
//        countryList?.let {
//            val countryData = it.find { country -> country.name.common.equals(query, ignoreCase = true) }
//            countryData?.let { displayCountryDetails(it) }
//            // Set initial visibility of the ListView to GONE
//            countryListRv.visibility = View.GONE
//        }
//    }
//
//    private fun displayCountryDetails(countryData: CountryData) {
//        binding.flagNameTxt.text = countryData.name.common
//
//        binding.nameTxt.text = "Country: " + countryData.name.common
//        binding.officialTxt.text = "Official: " + countryData.name.official
//        binding.capitalTxt.text = "Capital: " + countryData.capital?.joinToString(", ")
//
//        countryData.currencies?.forEach { (currencyCode, currency) ->
//            binding.currenciesTxt.text = "Currency: " + currency.name
//            binding.currenciesSymbolTxt.text = "Currency Symbol: " + currency.symbol
//            binding.currenciesCodeTxt.text = "Currency Code: " + currencyCode
//        }
//
//        binding.regionTxt.text = "Region: " + countryData.region
//        binding.timezonesTxt.text = "Timezones: " + countryData.timezones?.joinToString(", ")
//
//        Picasso.get()
//            .load(countryData.flags.name)
//            .into(binding.flagImageView)
//    }
//
//}

//WORKING OVERALL USING RECYCLEVIEW BUT NOT USING BINDING

//class MainActivity : AppCompatActivity() {
//    lateinit var countryListRv: RecyclerView
//    lateinit var countryAdapter: CountryAdapter
//    lateinit var countryList: ArrayList<CountryDataRecyleView>
//    lateinit var searchView: SearchView
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        countryListRv = findViewById(R.id.countryListTxt)
//        searchView = findViewById(R.id.searchView)
//        countryList = ArrayList()
//        // Initialize the adapter with the country list and a click listener for displaying country details
//        countryAdapter = CountryAdapter(countryList) { selectedCountryData ->
//            // Display the details of the selected country
//            displayCountryDetails(selectedCountryData)
//            // Hide the RecyclerView after selecting a country
//            countryListRv.visibility = View.GONE
//        }
//
//        countryListRv.layoutManager = LinearLayoutManager(this)
//        countryListRv.adapter = countryAdapter
//
//        countryListRv.visibility = View.VISIBLE
//
//        // Fetch country names from API and add to the list
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        responseLiveData.observe(this, Observer { response ->
//            val countries = response.body()
//            countries?.forEach { countryData ->
//                countryList.add(
//                    CountryDataRecyleView(
//                        name = countryData.name.common,
//                        official = countryData.name.official,
//                        capital = countryData.capital,
//                        currencies = countryData.currencies,
//                        region = countryData.region,
//                        timezones = countryData.timezones,
//                        flagUrl = countryData.flags.name
//                    )
//                )
//            }
//            // Sort the country list alphabetically by name
//            countryList.sortBy { it.name }
//            // Notify the adapter that the data set has changed
//            countryAdapter.notifyDataSetChanged()
//            // Setup the search view with the updated country list
//            setupSearchView(countryList)
//        })
//
//        // Search View
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Handle enter key press here
//                if (!query.isNullOrEmpty()) {
//                    // Filter the adapter with the query
//                    countryAdapter.filter.filter(query)
//                    countryListRv.visibility = View.VISIBLE
//                } else {
//                    countryListRv.visibility = View.GONE
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Filter the adapter with the new text
//                countryAdapter.filter.filter(newText)
//
//                if (newText.isNullOrEmpty()) {
//                    countryListRv.visibility = View.GONE
//                } else {
//                    countryListRv.visibility = View.VISIBLE
//                }
//                return true
//            }
//        })
//
//        searchView.setOnSearchClickListener {
//            countryListRv.visibility = View.VISIBLE
//        }
//
//        searchView.setOnCloseListener {
//            countryListRv.visibility = View.GONE
//            false
//        }
//
//    }
//
//    // Function to set up the search view with the country list
//    private fun setupSearchView(countryList: List<CountryDataRecyleView>) {
//        // Setting up the search view listener
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Call searchCountry function when the query is submitted
//                searchCountry(query ?: "", countryList)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Update the adapter with filtered list but don't display details
//                // Call searchCountry function when the query text changes
//                val filteredList = countryList.filter { country -> country.name.contains(newText ?: "", ignoreCase = true) }
//                countryAdapter.updateList(filteredList)
//                countryListRv.visibility = if (filteredList.isEmpty()) View.GONE else View.VISIBLE
//                return true
//            }
//        })
//
//        searchView.setOnSearchClickListener {
//            countryListRv.visibility = View.VISIBLE
//        }
//
//        searchView.setOnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                searchCountry(searchView.query.toString(), countryList)
//                return@setOnKeyListener true
//            }
//            false
//        }
//    }
//
//
//    // Function to search for a country in the list
//    private fun searchCountry(query: String, countryList: List<CountryDataRecyleView>?) {
//        countryList?.let {
//            // Filter the country list based on the query
//            val filteredList = it.filter { country -> country.name.contains(query, ignoreCase = true) }
//            // Update the adapter with the filtered list
//            countryAdapter.updateList(filteredList)
//            // Show or hide the RecyclerView based on the filtered list
//            countryListRv.visibility = if (filteredList.isEmpty()) View.GONE else View.VISIBLE
//
//            // Display details if there's an exact match
//            val exactMatch = filteredList.find { country -> country.name.equals(query, ignoreCase = true) }
//            exactMatch?.let {
//                displayCountryDetails(it)
//                countryListRv.visibility = View.GONE
//            }
//
//        }
//    }
//
//    private fun displayCountryDetails(countryData: CountryDataRecyleView) {
//        binding.flagNameTxt.text = countryData.name
//        binding.nameTxt.text = "Country: " + countryData.name
//        binding.officialTxt.text = "Official: " + countryData.official
//        binding.capitalTxt.text = "Capital: " + countryData.capital?.joinToString(", ")
//
//        countryData.currencies?.forEach { (currencyCode, currency) ->
//            binding.currenciesTxt.text = "Currency: " + currency.name
//            binding.currenciesSymbolTxt.text = "Currency Symbol: " + currency.symbol
//            binding.currenciesCodeTxt.text = "Currency Code: " + currencyCode
//        }
//
//        binding.regionTxt.text = "Region: " + countryData.region
//        binding.timezonesTxt.text = "Timezones: " + countryData.timezones?.joinToString(", ")
//
//        Picasso.get()
//            .load(countryData.flagUrl)
//            .into(binding.flagImageView)
//    }
//
//}

//WORKING OVERALL WITHOUT DAGGER HILT

//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var countryAdapter: CountryAdapter
//    // List to hold country data
//    private lateinit var countryList: ArrayList<CountryDataRecyleView>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Inflate the layout using view binding
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Initialize the country list
//        countryList = ArrayList()
//
//        // Initialize the adapter with the country list and a click listener for displaying country details
//        countryAdapter = CountryAdapter(countryList) { selectedCountryData ->
//            // Display the details of the selected country
//            displayCountryDetails(selectedCountryData)
//            // Hide the RecyclerView after selecting a country
//            binding.countryListTxt.visibility = View.GONE
//        }
//
//        // Set up the RecyclerView
//        binding.countryListTxt.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = countryAdapter
//            visibility = View.GONE
//        }
//
//        // Fetch country names from API and add to the list
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        // Observe the API response and update the country list
//        responseLiveData.observe(this, Observer { response ->
//            val countries = response.body()
//            countries?.forEach { countryData ->
//                countryList.add(
//                    CountryDataRecyleView(
//                        name = countryData.name.common,
//                        official = countryData.name.official,
//                        capital = countryData.capital,
//                        currencies = countryData.currencies,
//                        region = countryData.region,
//                        timezones = countryData.timezones,
//                        flagUrl = countryData.flags.name
//                    )
//                )
//            }
//            // Sort the country list alphabetically by name
//            countryList.sortBy { it.name }
//            // Notify the adapter that the data set has changed
//            countryAdapter.notifyDataSetChanged()
//            // Set up the search view with the updated country list
//            setupSearchView(countryList)
//        })
//
//        setupSearchView(countryList)
//    }
//
//    // Set up the search view to filter and display country details
//    private fun setupSearchView(countryList: List<CountryDataRecyleView>) {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Call searchCountry function when the query is submitted
//                searchCountry(query ?: "", countryList)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Update the adapter with filtered list but don't display details
//                val filteredList = countryList.filter { country -> country.name.contains(newText ?: "", ignoreCase = true) }
//                countryAdapter.updateList(filteredList)
//                binding.countryListTxt.visibility = if (filteredList.isEmpty()) View.GONE else View.VISIBLE
//                return true
//            }
//        })
//
//        binding.searchView.setOnSearchClickListener {
//
//            binding.countryListTxt.visibility = View.VISIBLE
//        }
//
//        binding.searchView.setOnKeyListener { _, keyCode, event ->
//
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                searchCountry(binding.searchView.query.toString(), countryList)
//                return@setOnKeyListener true
//            }
//            false
//        }
//
//        binding.searchView.setOnCloseListener {
//
//            binding.countryListTxt.visibility = View.GONE
//            false
//        }
//    }
//
//    // Search for a country in the list and display details if there's an exact match
//    private fun searchCountry(query: String, countryList: List<CountryDataRecyleView>?) {
//        countryList?.let {
//            // Filter the country list based on the query
//            val filteredList = it.filter { country -> country.name.contains(query, ignoreCase = true) }
//            // Update the adapter with the filtered list
//            countryAdapter.updateList(filteredList)
//            // Show or hide the RecyclerView based on the filtered list
//            binding.countryListTxt.visibility = if (filteredList.isEmpty()) View.GONE else View.VISIBLE
//            // Display details if there's an exact match
//            val exactMatch = filteredList.find { country -> country.name.equals(query, ignoreCase = true) }
//            exactMatch?.let {
//                displayCountryDetails(it)
//
//                binding.countryListTxt.visibility = View.GONE
//            }
//        }
//    }
//
//    private fun displayCountryDetails(countryData: CountryDataRecyleView) {
//        with(binding) {
//            flagNameTxt.text = countryData.name
//            nameTxt.text = "Country: " + countryData.name
//            officialTxt.text = "Official: " + countryData.official
//            capitalTxt.text = "Capital: " + countryData.capital?.joinToString(", ")
//            currenciesTxt.text = "Currency: " + countryData.currencies?.values?.firstOrNull()?.name
//            currenciesSymbolTxt.text = "Currency Symbol: " + countryData.currencies?.values?.firstOrNull()?.symbol
//            currenciesCodeTxt.text = "Currency Code: " + countryData.currencies?.keys?.firstOrNull()
//            regionTxt.text = "Region: " + countryData.region
//            timezonesTxt.text = "Timezones: " + countryData.timezones?.joinToString(", ")
//
//            Picasso.get().load(countryData.flagUrl).into(flagImageView)
//        }
//    }
//}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val countryViewModel: CountryViewModel by viewModels()
    private lateinit var countryDetailsView: CountryDetailsView
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set LayoutManager
        binding.countryListTxt.layoutManager = LinearLayoutManager(this)

        // Observe the RecyclerView visibility
        countryViewModel.recyclerViewVisibility.observe(this) { visibility ->
            binding.countryListTxt.visibility = visibility
        }

        // Observe the country list from the ViewModel
        countryViewModel.allCountries.observe(this) { countryList ->
            if (countryList != null) {
                val adapter = CountryAdapter(countryList,
                    onItemClicked = { countryName ->
                        countryViewModel.filterCountryDetails(countryName)
                    },
                    onHideAdapter = {
                        binding.countryListTxt.visibility = View.GONE
                    }
                )
                binding.countryListTxt.adapter = adapter
            }
        }


//          countryViewModel.countryFlagName.observe(this) { displayFlagName ->
//              countryViewModel.countryName.observe(this) { displayCountryName ->
//                  countryViewModel.countryOfficialName.observe(this) { displayOfficial ->
//                      countryViewModel.countryCapital.observe(this) { displayCapital ->
//                          countryViewModel.countryCurrencies.observe(this) { displayCurrencies ->
//                              countryViewModel.countryCurrenciesSymbol.observe(this) { displayCurrenciesSymbol ->
//                                  countryViewModel.countryCurrenciesCode.observe(this) { displayCurrenciesCode ->
//                                      countryViewModel.countryRegion.observe(this) { displayRegion ->
//                                          countryViewModel.countryTimeZone.observe(this) { displayTimezone ->
//                                              binding.apply {
//                                                  flagNameTxt.text = displayFlagName.toString()
//                                                  nameTxt.text = displayCountryName.toString()
//                                                  officialTxt.text = displayOfficial.toString()
//                                                  capitalTxt.text = displayCapital.toString()
//                                                  currenciesTxt.text = displayCurrencies.toString()
//                                                  currenciesSymbolTxt.text =
//                                                      displayCurrenciesSymbol.toString()
//                                                  currenciesCodeTxt.text =
//                                                      displayCurrenciesCode.toString()
//                                                  regionTxt.text = displayRegion.toString()
//                                                  timezonesTxt.text = displayTimezone.toString()
////                                                  Log.i("TAG", displayFlagName.toString())
////                                                  Log.i("TAG", displayCountryName.toString())
////                                                  Log.i("TAG", displayOfficial.toString())
////                                                  Log.i("TAG", displayCapital.toString())
////                                                  Log.i("TAG", displayCurrencies.toString())
////                                                  Log.i("TAG", displayCurrenciesSymbol.toString())
////                                                  Log.i("TAG", displayCurrenciesCode.toString())
////                                                  Log.i("TAG", displayRegion.toString())
////                                                  Log.i("TAG", displayTimezone.toString())
//                                              }
//                                          }
//                                      }
//                                  }
//                              }
//                          }
//                      }
//                  }
//              }
//          }

        countryViewModel.countryDetails.observe(this) {
            binding.apply {
                flagNameTxt.text = countryViewModel.countryFlagName.value.toString()
                Picasso.get().load(countryViewModel.countryFlagImage.value).into(flagImageView)
                nameTxt.text = countryViewModel.countryName.value.toString()
                officialTxt.text = countryViewModel.countryOfficialName.value.toString()
                capitalTxt.text = countryViewModel.countryCapital.value.toString()
                currenciesTxt.text = countryViewModel.countryCurrencies.value.toString()
                currenciesSymbolTxt.text = countryViewModel.countryCurrenciesSymbol.value.toString()
                currenciesCodeTxt.text = countryViewModel.countryCurrenciesCode.value.toString()
                regionTxt.text = countryViewModel.countryRegion.value.toString()
                timezonesTxt.text = countryViewModel.countryTimeZone.value.toString()
            }
        }

        // SearchView listener
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    countryViewModel.filterCountryDetails(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    countryViewModel.filterCountryDetails(it)
//                }
                return false
            }
        })

        // Show RecyclerView when searchView is clicked
        binding.searchView.setOnClickListener {
            countryViewModel.showRecyclerView()
        }

        // Reset RecyclerView visibility when SearchView is closed
        binding.searchView.setOnCloseListener {
            countryViewModel.hideRecyclerView()
            false // Return false to proceed with default close behavior
        }

        // Fetch countries when the activity is created
        countryViewModel.fetchCountries()

        //        countryViewModel.countries.observe(this) { displayData ->
//            updateUI(displayData)
//        }

        // Initialize the adapter with empty lists
//        adapter = CountryAdapter()
//        binding.countryListTxt.adapter = adapter
//
//        countryViewModel.countries.observe(this) { displayData ->
//            adapter.setCountries(displayData)
//        }
//
//        countryViewModel.fetchCountries()

//        // Initialize the ViewModel
//        countryViewModel.countries.observe(this, Observer { details ->
//            binding.countryDetailsView.setCountryDetails(details)
//        })
//
//        // Fetch countries when the activity is created
//        countryViewModel.fetchCountries()

        // Initialize binding
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Observe the ViewModel and update UI
//        countryViewModel.countries.observe(this) { displayData ->
//            countryUIHandler.updateUI(displayData)
//        }
    }

//    private fun updateUI(displayData: List<CountryDataRecyleView>) {
//        binding.apply {
//            val flagNames = displayData.joinToString(", ") { it.name }
//            flagNameTxt.text = flagNames
//
//            displayData.forEach { country ->
//                Picasso.get().load(country.flagUrl).into(flagImageView)
//            }
//
//            val countryNames = displayData.joinToString(", ") { it.name }
//            nameTxt.text = countryNames
//
//            val countryOfficial = displayData.joinToString(", ") { it.official }
//            officialTxt.text = countryOfficial
//
//            val countryCapital = displayData.joinToString(", ") { it.capital.toString() }
//            capitalTxt.text = countryCapital
//
//            val countryCurrencies  = displayData.joinToString(", ") { it.currencies?.values?.firstOrNull()?.name.toString() }
//            currenciesTxt.text = countryCurrencies
//
//            val countryCurrenciesSymbol  = displayData.joinToString(", ") { it.currencies?.values?.firstOrNull()?.symbol.toString() }
//            currenciesSymbolTxt.text = countryCurrenciesSymbol
//
//            val countryCurrenciesCode  = displayData.joinToString(", ") { it.currencies?.keys?.firstOrNull().toString() }
//            currenciesCodeTxt.text = countryCurrenciesCode
//
//            val countryRegion = displayData.joinToString(", ") { it.region }
//            regionTxt.text = countryRegion
//
//            val countryTimeZones = displayData.joinToString(", ") { it.timezones.toString() }
//            timezonesTxt.text = countryTimeZones
//
//            Log.i("TAG", displayData.toString())
//            //val adapter = CountryAdapter()
//        }
    }

//    private fun updateUI(displayData: CountryDisplayData) {
//        binding.apply {
//            flagNameTxt.text = displayData.flagNames
//            nameTxt.text = displayData.countryNames
//            officialTxt.text = displayData.countryOfficial
//            capitalTxt.text = displayData.countryCapital
//            currenciesTxt.text = displayData.countryCurrencies
//            currenciesSymbolTxt.text = displayData.countryCurrenciesSymbol
//            currenciesCodeTxt.text = displayData.countryCurrenciesCode
//            regionTxt.text = displayData.countryRegion
//            timezonesTxt.text = displayData.countryTimeZones
//        }
//    }


//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var CountryDetails: TextView
//    private lateinit var CountryDetails2: TextView
//    private lateinit var CountryDetails3: TextView
//    private lateinit var CountryDetails4: TextView
//    private lateinit var CountryDetails5: TextView
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
//        CountryDetails = findViewById(R.id.nameTxt)
//        CountryDetails2 = findViewById(R.id.capitalTxt)
//        CountryDetails3 = findViewById(R.id.currenciesTxt)
//        CountryDetails4 = findViewById(R.id.regionTxt)
//        CountryDetails5 = findViewById(R.id.timezonesTxt)
//        val retrofit = RetrofitInstance.getRetrofitInstance().create(CountryApiService::class.java)
//        val responseLiveData: LiveData<Response<Countries>> = liveData {
//            val response = retrofit.getCountries()
//            emit(response)
//        }
//
//        responseLiveData.observe(this, Observer {
//            val countryList = it.body()
//            if (countryList != null) {
//                countryList?.forEach(){
//                        countryData: CountryData ->
//                    CountryDetails.text = countryData.name.official
//                    CountryDetails2.text = countryData.capital.toString()
//                    countryData.currencies?.forEach() { (currencyCode, currency) ->
//                        CountryDetails3.text = currency.name
//                    }
//                    CountryDetails4.text = countryData.region
//                    CountryDetails5.text = countryData.timezones.toString()
//
//                    Log.i("TAG",countryData.name.common)
//                }
//
//            }
//        })
//
//    }
//
//}


