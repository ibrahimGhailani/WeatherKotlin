package space.ibrahim.weatherkotlin.data

import retrofit.http.GET
import retrofit.http.Field
import rx.Observable
import space.ibrahim.weatherkotlin.data.model.OpenWeatherResponseModel

/**
 * Created by ibrahim on 2/24/16.
 */

interface WeatherRetrofitService {

    @GET("find/")
    fun getWeather(@Field("q") city: String,
                   @Field("type") type: String,
                   @Field("mode") mode: String,
                   @Field("appid") appid: String
    ): Observable<OpenWeatherResponseModel>
}