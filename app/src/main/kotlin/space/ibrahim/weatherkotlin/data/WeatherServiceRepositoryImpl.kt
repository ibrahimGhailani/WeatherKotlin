package space.ibrahim.weatherkotlin.data

import rx.Observable
import space.ibrahim.weatherkotlin.data.model.OpenWeatherResponseModel
import space.ibrahim.weatherkotlin.domain.WeatherServiceRepository

/**
 * Created by ibrahim on 2/24/16.
 */

class WeatherServiceRepositoryImpl(weatherRetrofitService: WeatherRetrofitService) :
        WeatherServiceRepository {
    override fun getWeather(city: String): Observable<OpenWeatherResponseModel> {
        return weatherRetrofitService.getWeather(city, "accurate", "json", "metric", "44db6a862fba0b067b1930da0d769e98").map { x -> x }
    }

    val weatherRetrofitService = weatherRetrofitService
}
