package com.example.retrofit.retro.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.retro.model.CountryData
import com.example.retrofit.retro.model.CountryDataRecyleView
import com.squareup.picasso.Picasso

//class CountryAdapter(context: Context, private val countryList: List<String>) : ArrayAdapter<String>(context, 0, countryList) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
//        val countryName = view.findViewById<TextView>(android.R.id.text1)
//        val country = countryList[position]
//
//        countryName.text = country
//
//        view.setOnClickListener {
//            // Handle the click event
//            displayCountryDetails(country)
//        }
//
//        return view
//    }
//
//    private fun displayCountryDetails(country: String) {
//        // Implement your logic to display country details
//        Toast.makeText(context, "Clicked on: $country", Toast.LENGTH_SHORT).show()
//    }
//}

// Original
//class CountryAdapter(
//    private var countryList: List<CountryDataRecyleView>,
//    private val onItemClick: (CountryDataRecyleView) -> Unit
//) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {
//
//    private var countryListFull: List<CountryDataRecyleView> = ArrayList(countryList)
//
//    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val countryName: TextView = itemView.findViewById(android.R.id.text1)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
//        return CountryViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        val countryData = countryList[position]
//        holder.countryName.text = countryData.name
//        holder.itemView.setOnClickListener {
//            onItemClick(countryData)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return countryList.size
//    }
//
//    // Override the getFilter method to provide custom filtering logic
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            // Perform filtering based on the constraint
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                // If the constraint is empty, return the full list
//                val filteredList = if (constraint.isNullOrEmpty()) {
//                    countryListFull
//                } else {
//                    // Convert the constraint to lowercase and trim it
//                    val filterPattern = constraint.toString().lowercase().trim()
//                    // Filter the full list based on the filter pattern
//                    countryListFull.filter {
//                        it.name.lowercase().contains(filterPattern)
//                    }
//                }
//                // Create a FilterResults object to hold the filtered list
//                val results = FilterResults()
//                results.values = filteredList
//                return results
//            }
//
//            // Publish the filtering results
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                // Update the country list with the filtered results
//                countryList = results?.values as List<CountryDataRecyleView>
//                // Notify the adapter that the data set has changed
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    // Function to update the country list and notify the adapter
//    fun updateList(newList: List<CountryDataRecyleView>) {
//        countryList = newList
//        notifyDataSetChanged()
//    }
//
//}

// WORKING ADAPTER OVERALL WITHOUT BINDING
//class CountryAdapter(
//    private var countryList: List<CountryDataRecyleView>,
//    private val onItemClick: (CountryDataRecyleView) -> Unit
//) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {
//
//    private var countryListFull: List<CountryDataRecyleView> = ArrayList(countryList)
//
//    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val countryName: TextView = itemView.findViewById(android.R.id.text1)
//        val flagImage: ImageView = itemView.findViewById(R.id.flagImageListView)
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
//        return CountryViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        val countryData = countryList[position]
//        holder.countryName.text = countryData.name
//        Picasso.get().load(countryData.flagUrl).into(holder.flagImage)
//
//        holder.itemView.setOnClickListener {
//            onItemClick(countryData)
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        return countryList.size
//    }
//
//    // Override the getFilter method to provide custom filtering logic
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            // Perform filtering based on the constraint
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                // If the constraint is empty, return the full list
//                val filteredList = if (constraint.isNullOrEmpty()) {
//                    countryListFull
//                } else {
//                    // Convert the constraint to lowercase and trim it
//                    val filterPattern = constraint.toString().lowercase().trim()
//                    // Filter the full list based on the filter pattern
//                    countryListFull.filter {
//                        it.name.lowercase().contains(filterPattern)
//                    }
//                }
//                // Create a FilterResults object to hold the filtered list
//                val results = FilterResults()
//                results.values = filteredList
//                return results
//            }
//
//            // Publish the filtering results
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                // Update the country list with the filtered results
//                countryList = results?.values as List<CountryDataRecyleView>
//                // Notify the adapter that the data set has changed
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    // Function to update the country list and notify the adapter
//    fun updateList(newList: List<CountryDataRecyleView>) {
//        countryList = newList.sortedBy { it.name }
//        notifyDataSetChanged()
//    }
//
//}

class CountryAdapter(
    private val countryList: List<CountryData>,
    private val onItemClicked: (String) -> Unit,
    private val onHideAdapter: () -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View, val onItemClicked: (String) -> Unit, val onHideAdapter: () -> Unit) : RecyclerView.ViewHolder(view) {
        val countryName: TextView = view.findViewById(android.R.id.text1)
        val flagImage: ImageView = view.findViewById(R.id.flagImageListView)
        //val button: Button = view.findViewById(R.id.myButton)

        fun bind(country: CountryData) {
            itemView.setOnClickListener {
                onItemClicked(country.name.common)
                onHideAdapter()
            }
            itemView.setOnClickListener {
                it.isSelected = !it.isSelected // Toggle button state
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view, onItemClicked, onHideAdapter)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.countryName.text = country.name.common
        Picasso.get().load(country.flags.name).into(holder.flagImage)
        holder.bind(country)
    }

    override fun getItemCount() = countryList.size

}




//class CountryAdapter(
//    private val flagNames: List<String>,
//    private val countryNames: List<String>,
//    private val countryOfficial: List<String>,
//    private val countryCapital: List<String>
//
//) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
//    private var countryList: List<CountryDataRecyleView> = listOf()
//
//    fun setCountries(countries: List<CountryDataRecyleView>) {
//        this.countryList = countries
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CountryViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = countryList.size
//
//    class CountryViewHolder(private val binding: ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(countryData: CountryDataRecyleView) {
//            with(binding) {
////                val flagNames: TextView = binding.nameTxt
////                officialTxt.text
////                capitalTxt.text
////                currenciesTxt.text
//                nameTxt.text = countryData.name
//                officialTxt.text = "Official: " + countryData.official
//                capitalTxt.text = "Capital: " + countryData.capital?.joinToString(", ")
//                currenciesTxt.text = "Currency: " + countryData.currencies?.values?.firstOrNull()?.name
//                currenciesSymbolTxt.text = "Currency Symbol: " + countryData.currencies?.values?.firstOrNull()?.symbol
//                currenciesCodeTxt.text = "Currency Code: " + countryData.currencies?.keys?.firstOrNull()
//                regionTxt.text = "Region: " + countryData.region
//                timezonesTxt.text = "Timezones: " + countryData.timezones?.joinToString(", ")
//                Picasso.get().load(countryData.flagUrl).into(binding.flagImageView)
//
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        holder.bind(countryList[position])
//        //holder.nameTxt.text = name[position]
//    }
//
//}


//class CountryAdapter(
//    private var countryList: List<CountryDataRecyleView>,
//    private val onItemClick: (CountryDataRecyleView) -> Unit
//) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {
//
//    private var countryListFull: List<CountryDataRecyleView> = ArrayList(countryList)
//
//    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val countryName: TextView = itemView.findViewById(R.id.countryNameTextView)
//        val flagImage: ImageView = itemView.findViewById(R.id.flagImageListView)
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
//        return CountryViewHolder(view)
//    }
//
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        val countryData = countryList[position]
//        holder.countryName.text = countryData.name
//        Picasso.get().load(countryData.flagUrl).into(holder.flagImage)
//
//        holder.itemView.setOnClickListener {
//            onItemClick(countryData)
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        return countryList.size
//    }
//
//    // Override the getFilter method to provide custom filtering logic
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            // Perform filtering based on the constraint
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                // If the constraint is empty, return the full list
//                val filteredList = if (constraint.isNullOrEmpty()) {
//                    countryListFull
//                } else {
//                    // Convert the constraint to lowercase and trim it
//                    val filterPattern = constraint.toString().lowercase().trim()
//                    // Filter the full list based on the filter pattern
//                    countryListFull.filter {
//                        it.name.lowercase().contains(filterPattern)
//                    }
//                }
//                // Create a FilterResults object to hold the filtered list
//                val results = FilterResults()
//                results.values = filteredList
//                return results
//            }
//
//            // Publish the filtering results
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                // Update the country list with the filtered results
//                countryList = results?.values as List<CountryDataRecyleView>
//                // Notify the adapter that the data set has changed
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    // Function to update the country list and notify the adapter
//    fun updateList(newList: List<CountryDataRecyleView>) {
//        countryList = newList
//        notifyDataSetChanged()
//    }
//
//}


















