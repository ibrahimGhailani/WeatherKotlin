package space.ibrahim.weatherkotlin.data.model

/**
 * Created by ibrahim on 2/24/16.
 */
data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String,
        val temperature: Temperature
)

