package indep.projects.rickandmorty.feed

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import indep.projects.rickandmorty.database.CharacterDao
import indep.projects.rickandmorty.network.CoroutineService
import indep.projects.rickandmorty.network.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.ConnectException

class RickAndMortyViewModel(private val service: CoroutineService, private val characterDao: CharacterDao) : ViewModel(){
    private val _characterData = MutableLiveData<List<Character>>()
    val characterData : LiveData<List<Character>> = _characterData

    fun fetchCharacters() {
            try {
                viewModelScope.launch {
                    val rickAndMortyObjectList = service.getRickAndMortyObjects().results
                    characterDao.insertCharacters(rickAndMortyObjectList)
                    _characterData.postValue(rickAndMortyObjectList)
                }
            }
            catch(exception: Exception){
                _characterData.postValue(characterDao.getAllCharacters())
            }
    }
}