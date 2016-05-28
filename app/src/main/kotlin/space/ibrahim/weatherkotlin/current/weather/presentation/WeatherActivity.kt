package space.ibrahim.weatherkotlin.current.weather.presentation

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_weather.*
import space.ibrahim.weatherkotlin.R
import space.ibrahim.weatherkotlin.common.App
import space.ibrahim.weatherkotlin.current.weather.data.model.City
import space.ibrahim.weatherkotlin.current.weather.injection.DaggerWeatherComponent
import space.ibrahim.weatherkotlin.current.weather.injection.WeatherComponent
import space.ibrahim.weatherkotlin.current.weather.injection.WeatherModule
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherView {

    @Inject
    lateinit var weatherPresenter: WeatherPresenter;
    val cities = mutableListOf<City>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        initDaggerComponent()
        initViews()
    }

    private fun initViews() {
        searchButton.setOnClickListener({ searchForCity(cityEditText.text.toString()) })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WeatherAdapter(cities)
    }

    private fun initDaggerComponent() {
        val component: WeatherComponent = DaggerWeatherComponent.builder()
                .appComponent((application as App).appComponent)
                .weatherModule(WeatherModule(this))
                .build();

        component.inject(this);
    }

    override fun showLoading(loading: Boolean) {
        when (loading) {
            true -> weatherLoadingTextView.visibility = View.VISIBLE
            false -> weatherLoadingTextView.visibility = View.GONE
        }
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun displayResults(cities: List<City>) {
        this.cities.clear()
        this.cities.addAll(cities)
        recyclerView.adapter.notifyDataSetChanged()
    }

    private fun searchForCity(city: String) {
        weatherPresenter.searchForCity(city)
    }
}