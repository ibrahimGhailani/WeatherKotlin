package space.ibrahim.weatherkotlin.presentation

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import space.ibrahim.weatherkotlin.domain.WeatherService

/**
 * Created by ibrahim on 2/24/16.
 */

class WeatherPresenter(weatherView: WeatherView, weatherService: WeatherService) {

    val weatherView: WeatherView = weatherView
    val weatherService: WeatherService = weatherService

    fun searchForCity(city: String) {
        //TODO: call service
        weatherView.showLoading(true)
        if (city.isNullOrBlank())
            weatherService.getWeather(city)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        city ->
                        city.temperature
                        weatherView.showLoading(false)
                    }

    }

}

