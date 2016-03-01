package space.ibrahim.weatherkotlin.data.model

/**
 * Created by ibrahim on 2/24/16.
 */
data class OpenWeatherResponseModel(
        val message: String,
        val cod: Int,
        val count: Int,
        val list: List<City>
)