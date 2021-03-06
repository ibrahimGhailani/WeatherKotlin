package space.ibrahim.weatherkotlin.current.weather.presentation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import space.ibrahim.weatherkotlin.common.SchedulerConfiguration;
import space.ibrahim.weatherkotlin.current.weather.data.model.City;
import space.ibrahim.weatherkotlin.current.weather.data.model.Coord;
import space.ibrahim.weatherkotlin.current.weather.data.model.OpenWeatherResponseModel;
import space.ibrahim.weatherkotlin.current.weather.data.model.Temperature;
import space.ibrahim.weatherkotlin.current.weather.data.model.Weather;
import space.ibrahim.weatherkotlin.current.weather.domain.WeatherService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherPresenterTest {

    WeatherPresenter presenter;
    WeatherView view;
    WeatherService service;

    @Before
    public void beforeEachTest() {
        view = mock(WeatherView.class);
        service = mock(WeatherService.class);
        presenter = new WeatherPresenter(view, service,
                new SchedulerConfiguration(Schedulers.immediate(), Schedulers.immediate()));
    }

    @Test
    public void shouldDisplayTempInView() {
        List<City> cities = new ArrayList<>();
        List<Weather> w = new ArrayList<>();
        w.add(new Weather(1, "", "", ""));
        Temperature t = new Temperature("10", "10", "10", "10", "10");
        Coord c = new Coord(24.0, 45.0);
        cities.add(new City(1, "riyadh", c, t, w));
        OpenWeatherResponseModel response = new OpenWeatherResponseModel("", 1, 1, cities);

        when(service.getWeather("riyadh")).thenReturn(Observable.just(response));

        presenter.searchForCity("riyadh");
        verify(view).showLoading(true);
        verify(view).displayResults(cities);
    }

    @Test
    public void shouldDisplayNotFound() {

        List<City> cities = new ArrayList<>();
        OpenWeatherResponseModel response = new OpenWeatherResponseModel("", 0, 0, cities);

        when(service.getWeather("asdf")).thenReturn(Observable.just(response));

        presenter.searchForCity("asdf");
        verify(view).showLoading(true);
        verify(view).showSnackBar("Not found");
        verify(view).showLoading(false);
    }
}
