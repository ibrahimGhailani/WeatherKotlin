package space.ibrahim.weatherkotlin.current.weather.data

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel

interface WeatherRetrofitService {

    @GET("find")
    fun getWeather(@Query("q") city: String,
                   @Query("type") type: String,
                   @Query("mode") mode: String,
                   @Query("units") units: String,
                   @Query("appid") appid: String): Observable<OpenWeatherResponseModel>
}