package indep.projects.rickandmorty.dagger

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import indep.projects.rickandmorty.Constants
import indep.projects.rickandmorty.network.CoroutineService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideCoroutineService(): CoroutineService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(CoroutineService::class.java)
    }
}