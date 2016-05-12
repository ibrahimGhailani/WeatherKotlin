package space.ibrahim.weatherkotlin.current.weather.data

import rx.Observable
import space.ibrahim.weatherkotlin.common.Constants
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel
import space.ibrahim.weatherkotlin.current.weather.domain.WeatherServiceRepository

class WeatherServiceRepositoryImpl(weatherRetrofitService: WeatherRetrofitService) : WeatherServiceRepository {
    override fun getWeather(city: String): Observable<OpenWeatherResponseModel> {
        return weatherRetrofitService.getWeather(
                city,
                Constants.TYPE,
                Constants.MODE,
                Constants.UNITS,
                Constants.APP_ID)
                .map { x -> x }
    }

    val weatherRetrofitService = weatherRetrofitService
}