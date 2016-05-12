package space.ibrahim.weatherkotlin.current.weather.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import space.ibrahim.weatherkotlin.R
import space.ibrahim.weatherkotlin.common.App
import space.ibrahim.weatherkotlin.current.weather.injection.DaggerWeatherComponent
import space.ibrahim.weatherkotlin.current.weather.injection.WeatherComponent
import space.ibrahim.weatherkotlin.current.weather.injection.WeatherModule
import javax.inject.Inject


class WeatherActivity : AppCompatActivity(), WeatherView {

    @Inject
    lateinit var weatherPresenter: WeatherPresenter;
    lateinit var cityEditText: EditText
    lateinit var searchButton: Button
    lateinit var weatherResultTextView: TextView
    lateinit var weatherLoadingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        initDaggerComponent()
        init()
    }

    private fun initDaggerComponent() {
        var component: WeatherComponent = DaggerWeatherComponent.builder()
                .appComponent((application as App).appComponent)
                .weatherModule(WeatherModule(this))
                .build();

        component.inject(this);
    }

    private fun init() {
        cityEditText = findViewById(R.id.cityEditText) as EditText;
        searchButton = findViewById(R.id.searchButton) as Button;
        searchButton.setOnClickListener({ searchForCity(cityEditText.text.toString()) })
        weatherResultTextView = findViewById(R.id.weatherResultTextView) as TextView;
        weatherLoadingTextView = findViewById(R.id.weatherLoadingTextView) as TextView;
    }

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

    private fun searchForCity(city: String) {
        weatherPresenter.searchForCity(city)
    }
}