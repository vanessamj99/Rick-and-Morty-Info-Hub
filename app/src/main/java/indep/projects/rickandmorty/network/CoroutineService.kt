package indep.projects.rickandmorty.network

import indep.projects.rickandmorty.network.model.RickAndMortyObject
import retrofit2.http.GET

interface CoroutineService {
    @GET("character")
    suspend fun getRickAndMortyObjects(): RickAndMortyObject
}