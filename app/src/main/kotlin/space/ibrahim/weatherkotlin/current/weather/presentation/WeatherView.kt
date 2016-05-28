package space.ibrahim.weatherkotlin.current.weather.presentation

import space.ibrahim.weatherkotlin.current.weather.data.model.City

interface WeatherView {
    fun showToast(message: String)

    fun showSnackBar(message: String)

    fun showLoading(loading: Boolean)

    fun displayResults(cities: List<City>)
}