package space.ibrahim.weatherkotlin.presentation

interface WeatherView {
    fun showToast(message: String)

    fun showTemperature(temperature: String?)

    fun showLoading(loading: Boolean)
}