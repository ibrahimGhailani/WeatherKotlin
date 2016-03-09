package space.ibrahim.weatherkotlin.presentation

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import space.ibrahim.weatherkotlin.data.model.OpenWeatherResponseModel
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
        if (!city.isNullOrBlank()) {
            weatherService.getWeather(city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Subscriber<OpenWeatherResponseModel> () {

                        override fun onError(e: Throwable?) {
                            weatherView.showLoading(false)
                            e?.printStackTrace()
                        }

                        override fun onNext(response: OpenWeatherResponseModel) {
                            if (response.list.size > 0) {
                                weatherView.showLoading(false)
                                weatherView.showTemperature(response.list[0].main.temp)
                            } else {
                                weatherView.showTemperature("Not found")
                            }
                            weatherView.showLoading(false)
                        }

                        override fun onCompleted() {
                        }
                    })
        } else {
            weatherView.showLoading(false)
        }
    }
}