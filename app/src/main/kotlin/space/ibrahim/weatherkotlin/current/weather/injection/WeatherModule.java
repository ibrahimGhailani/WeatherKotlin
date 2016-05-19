package space.ibrahim.weatherkotlin.current.weather.injection;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import space.ibrahim.weatherkotlin.common.SchedulerConfiguration;
import space.ibrahim.weatherkotlin.current.weather.data.WeatherRetrofitService;
import space.ibrahim.weatherkotlin.current.weather.data.WeatherServiceRepositoryImpl;
import space.ibrahim.weatherkotlin.current.weather.domain.WeatherService;
import space.ibrahim.weatherkotlin.current.weather.domain.WeatherServiceRepository;
import space.ibrahim.weatherkotlin.current.weather.presentation.WeatherActivity;
import space.ibrahim.weatherkotlin.current.weather.presentation.WeatherPresenter;
import space.ibrahim.weatherkotlin.current.weather.presentation.WeatherView;

@Module
public class WeatherModule {

    WeatherActivity weatherActivity;

    public WeatherModule(WeatherActivity weatherActivity) {
        this.weatherActivity = weatherActivity;
    }

    @PerActivity
    @Provides
    WeatherView provideWeatherView() {
        return weatherActivity;
    }

    @PerActivity
    @Provides
    WeatherService provideWeatherService(WeatherServiceRepository repository) {
        return new WeatherService(repository);
    }

    @PerActivity
    @Provides
    WeatherRetrofitService provideWeatherRetrofitService(Retrofit retrofit) {
        return retrofit.create(WeatherRetrofitService.class);
    }

    @PerActivity
    @Provides
    WeatherServiceRepository provideWeatherServiceRepository(WeatherRetrofitService service) {
        return new WeatherServiceRepositoryImpl(service);
    }

    @PerActivity
    @Provides
    WeatherPresenter provideWeatherPresenter(WeatherView view, WeatherService service) {
        return new WeatherPresenter(view, service, new SchedulerConfiguration(Schedulers.io(), AndroidSchedulers.mainThread()));
    }
}
