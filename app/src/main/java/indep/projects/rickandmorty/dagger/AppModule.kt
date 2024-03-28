package indep.projects.rickandmorty.dagger

import android.app.Application
import androidx.core.app.AppLaunchChecker
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Module
import dagger.Provides
import indep.projects.rickandmorty.RMApplication
import indep.projects.rickandmorty.ViewModelFactory
import indep.projects.rickandmorty.database.AppDatabase
import indep.projects.rickandmorty.database.CharacterDao
import indep.projects.rickandmorty.feed.RickAndMortyViewModel
import indep.projects.rickandmorty.network.CoroutineService
import javax.inject.Singleton

@Module
class AppModule constructor(private val application: RMApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application {
        return application
    }
    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory<RickAndMortyViewModel>) : ViewModelProvider.Factory{
        return factory
    }

    @Provides
    fun provideRickAndMortyViewModel(service: CoroutineService, characterDao: CharacterDao): RickAndMortyViewModel{
        return RickAndMortyViewModel(service, characterDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "character-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: AppDatabase): CharacterDao{
        return database.characterDao()
    }
}