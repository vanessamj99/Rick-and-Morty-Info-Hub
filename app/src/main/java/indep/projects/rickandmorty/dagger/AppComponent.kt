package indep.projects.rickandmorty.dagger

import dagger.Component
import indep.projects.rickandmorty.RMApplication
import indep.projects.rickandmorty.feed.RickAndMortyFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Builder
    interface  Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }
    fun inject(app: RMApplication)
    fun inject(characterFragment: RickAndMortyFragment)
}