//package com.example.retrofit.retro
//
//import android.content.Context
//import android.util.AttributeSet
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import com.example.retrofit.R
//
//class CountryDetailsView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//
//    private val countryFlagName: TextView
//
//    private val countryFlag: ImageView
//        get() {
//            TODO()
//        }
//
//    private val countryName: TextView
//    private val countryOfficial: TextView
//    private val countryCapital: TextView
//    private val countryCurrencies: TextView
//    private val countryCurrenciesSymbol: TextView
//    private val countryCurrenciesCode: TextView
//    private val countryTimezone: TextView
//    private val countryRegion: TextView
//
//    init {
//        inflate(context, R.layout.activity_main, this)
//        orientation = VERTICAL
//        countryFlagName = findViewById(R.id.flagNameTxt)
//
//        //flagImageView
//
//        countryName = findViewById(R.id.nameTxt)
//        countryOfficial = findViewById(R.id.officialTxt)
//        countryCapital = findViewById(R.id.capitalTxt)
//        countryCurrencies = findViewById(R.id.currenciesTxt)
//        countryCurrenciesSymbol = findViewById(R.id.currenciesSymbolTxt)
//        countryCurrenciesCode = findViewById(R.id.currenciesCodeTxt)
//        countryRegion = findViewById(R.id.regionTxt)
//        countryTimezone = findViewById(R.id.timezonesTxt)
//
//    }
//
//    fun setCountryDetails(details: CountryDetails?) {
//        countryName.text = details?.name
//        countryCapital.text = details?.capital
//        // Set other fields...
//    }
//}

package com.example.retrofit.retro

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.retro.daggerhilt.CountryViewModel
import com.example.retrofit.retro.model.CountryDataRecyleView
import com.squareup.picasso.Picasso

class CountryDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: ActivityMainBinding

    init {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(context), this, true)
        orientation = VERTICAL
    }

    fun setCountryDetails(details: List<CountryDataRecyleView>) {
        details.forEach { detail ->
            binding.flagNameTxt.text = detail.name
            binding.nameTxt.text = detail.name
            binding.officialTxt.text = detail.official
            binding.capitalTxt.text = detail.capital?.joinToString(", ")
            binding.currenciesTxt.text = detail.currencies?.values?.joinToString(", ") { it.name }
            binding.currenciesSymbolTxt.text = detail.currencies?.values?.joinToString(", ") { it.symbol }
            binding.currenciesCodeTxt.text = detail.currencies?.keys?.joinToString(", ")
            binding.regionTxt.text = detail.region
            binding.timezonesTxt.text = detail.timezones?.joinToString(", ")

            // Load the flag image using Picasso
            Picasso.get().load(detail.flagUrl).into(binding.flagImageView)
        }
    }

}

