package space.ibrahim.weatherkotlin.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import space.ibrahim.weatherkotlin.R
import space.ibrahim.weatherkotlin.data.WeatherRetrofitService
import space.ibrahim.weatherkotlin.data.WeatherServiceRepositoryImpl
import space.ibrahim.weatherkotlin.domain.WeatherService

/**
 * Created by ibrahim on 2/24/16.
 */
class WeatherActivity : AppCompatActivity(), WeatherView {
    override fun showLoading(loading: Boolean) {
        when (loading) {
            true -> weatherLoadingTextView.visibility = View.VISIBLE
            false -> weatherLoadingTextView.visibility = View.GONE
        }
    }

    override fun showTemperature(temperature: String?) {
        weatherResultTextView.text = temperature
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    val weatherPresenter: WeatherPresenter = WeatherPresenter(this,
            WeatherService(
                    WeatherServiceRepositoryImpl(
                            getRetrofit())))

    private fun getRetrofit(): WeatherRetrofitService {
        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return retrofit.create(WeatherRetrofitService::class.java)
    }

    lateinit var cityEditText: EditText
    lateinit var searchButton: Button
    lateinit var weatherResultTextView: TextView
    lateinit var weatherLoadingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        init()
    }

    private fun init() {
        cityEditText = findViewById(R.id.cityEditText) as EditText;
        searchButton = findViewById(R.id.searchButton) as Button;
        searchButton.setOnClickListener({ searchForCity(cityEditText.text.toString()) })
        weatherResultTextView = findViewById(R.id.weatherResultTextView) as TextView;
        weatherLoadingTextView = findViewById(R.id.weatherLoadingTextView) as TextView;
    }

    private fun searchForCity(city: String) {
        weatherPresenter.searchForCity(city)
    }
}

val BASE_URL: String = "http://api.openweathermap.org/data/2.5/"