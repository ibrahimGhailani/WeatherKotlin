package space.ibrahim.weatherkotlin.current.weather.data.model

/**
 * Created by ibrahim on 2/24/16.
 */
data class OpenWeatherResponseModel(
        val message: String,
        var cod: Int,
        val count: Int,
        val list: List<City>
)