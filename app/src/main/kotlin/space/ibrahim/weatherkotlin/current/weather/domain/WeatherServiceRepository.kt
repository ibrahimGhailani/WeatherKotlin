package space.ibrahim.weatherkotlin.current.weather.domain

import rx.Observable
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel


interface WeatherServiceRepository {
    fun getWeather(city: String): Observable<OpenWeatherResponseModel>;
}