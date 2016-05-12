package space.ibrahim.weatherkotlin.current.weather.presentation

interface WeatherView {
    fun showToast(message: String)

    fun showTemperature(temperature: String?)

    fun showLoading(loading: Boolean)
}