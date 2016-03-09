package space.ibrahim.weatherkotlin.domain

import rx.Observable
import space.ibrahim.weatherkotlin.data.model.OpenWeatherResponseModel

/**
 * Created by ibrahim on 2/24/16.
 */

class WeatherService(weatherServiceRepository: WeatherServiceRepository) {
    val weatherServiceRepository = weatherServiceRepository

    fun getWeather(city: String): Observable<OpenWeatherResponseModel> {
        return weatherServiceRepository.getWeather(city)
    }
}