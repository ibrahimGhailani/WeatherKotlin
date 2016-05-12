package space.ibrahim.weatherkotlin.current.weather.data.model

data class Temperature(
        val temp: String,
        val tempMin: String,
        val tempMax: String,
        val pressure: String,
        val seaLevel: String,
        val grndLevel: String,
        val humidity: String
)