package space.ibrahim.weatherkotlin.current.weather.data.model

/**
 * Created by ibrahim on 2/24/16.
 */
data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val main: Temperature,
        val weather: List<Weather>
)

