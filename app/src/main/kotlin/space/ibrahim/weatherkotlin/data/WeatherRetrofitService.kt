package space.ibrahim.weatherkotlin.data

import retrofit.http.GET
import retrofit.http.Query
import rx.Observable
import space.ibrahim.weatherkotlin.data.model.OpenWeatherResponseModel

/**
 * Created by ibrahim on 2/24/16.
 */

interface WeatherRetrofitService {

    @GET("find")
    fun getWeather(@Query("q") city: String,
                   @Query("type") type: String,
                   @Query("mode") mode: String,
                   @Query("units") units: String,
                   @Query("appid") appid: String
    ): Observable<OpenWeatherResponseModel>
}