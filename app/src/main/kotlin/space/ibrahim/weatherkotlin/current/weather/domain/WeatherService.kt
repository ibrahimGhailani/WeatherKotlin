package space.ibrahim.weatherkotlin.current.weather.domain

import rx.Observable
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel


open class WeatherService(weatherServiceRepository: WeatherServiceRepository) {
    val weatherServiceRepository = weatherServiceRepository

    open fun getWeather(city: String): Observable<OpenWeatherResponseModel> {
        return weatherServiceRepository.getWeather(city)
    }
}