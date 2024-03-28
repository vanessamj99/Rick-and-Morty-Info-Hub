package indep.projects.rickandmorty

import android.app.Application
import indep.projects.rickandmorty.dagger.AppComponent
import indep.projects.rickandmorty.dagger.AppModule
import indep.projects.rickandmorty.dagger.DaggerAppComponent

class RMApplication: Application() {
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerAppComponent()
    }

    private fun initDaggerAppComponent(): AppComponent {
        appComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        return appComponent
    }
}