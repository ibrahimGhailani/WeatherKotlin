package space.ibrahim.weatherkotlin.current.weather.injection;

import dagger.Component
import space.ibrahim.weatherkotlin.common.injection.AppComponent
import space.ibrahim.weatherkotlin.current.weather.presentation.WeatherActivity

@PerActivity
@Component(modules = arrayOf(WeatherModule::class), dependencies = arrayOf(AppComponent::class))
interface WeatherComponent {
    fun inject(weatherActivity: WeatherActivity);
}