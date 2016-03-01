
package space.ibrahim.weatherkotlin.domain

import rx.Observable
import space.ibrahim.weatherkotlin.data.model.City

/**
 * Created by ibrahim on 2/24/16.
 */

interface WeatherServiceRepository {
    fun getWeather(city: String): Observable<City>;
}