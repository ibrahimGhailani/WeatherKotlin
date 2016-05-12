package space.ibrahim.weatherkotlin.common.injection

import dagger.Component
import retrofit2.Retrofit
import space.ibrahim.weatherkotlin.common.App
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {
    fun inject(app: App): App
    fun retrofit(): Retrofit
}