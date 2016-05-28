package space.ibrahim.weatherkotlin.current.weather.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import space.ibrahim.weatherkotlin.R
import space.ibrahim.weatherkotlin.current.weather.data.model.City

class WeatherAdapter(cities: MutableList<City>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    val cities = cities
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCity(cities.get(position))
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCity(city: City) {
            itemView.cityTextView.text = city.name
            itemView.weatherValueTextView.text = city.main.temp
            itemView.weatherDescriptionTextView.text = city.weather.get(0).description
        }
    }

}