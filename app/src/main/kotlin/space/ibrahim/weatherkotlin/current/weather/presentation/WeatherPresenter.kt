package space.ibrahim.weatherkotlin.current.weather.presentation

import rx.Subscriber
import space.ibrahim.weatherkotlin.common.SchedulerConfiguration
import space.ibrahim.weatherkotlin.current.weather.data.model.City
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel
import space.ibrahim.weatherkotlin.current.weather.domain.WeatherService

class WeatherPresenter(weatherView: WeatherView, weatherService: WeatherService, scheduler: SchedulerConfiguration) {

    val weatherView: WeatherView = weatherView
    val weatherService: WeatherService = weatherService
    val scheduler: SchedulerConfiguration = scheduler

    fun searchForCity(city: String) {
        weatherView.showLoading(true)
        if (!city.isNullOrBlank()) {
            weatherService.getWeather(city)
                    .subscribeOn(scheduler.subscriberScheduler)
                    .observeOn(scheduler.observerScheduler)
                    .subscribe(object : Subscriber<OpenWeatherResponseModel> () {

                        override fun onError(e: Throwable?) {
                            weatherView.showLoading(false)
                            weatherView.showSnackBar("Error")
                            e?.printStackTrace()
                        }

                        override fun onNext(response: OpenWeatherResponseModel) {
                            if (response.list.size > 0) {
                                weatherView.showLoading(false)
                                weatherView.displayResults(response.list)
                                weatherView.showSnackBar("Results: ")
                            } else {
                                weatherView.showSnackBar("Not found")
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