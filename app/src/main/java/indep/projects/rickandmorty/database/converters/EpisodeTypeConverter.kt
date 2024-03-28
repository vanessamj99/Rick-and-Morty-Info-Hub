package indep.projects.rickandmorty.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EpisodeTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromEpisode(location: List<String>?): String? {
        return gson.toJson(location)
    }
    @TypeConverter
    fun toEpisode(locationJson: String?): List<String>? {
        if(locationJson == null){
            return null
        }
        val type = object: TypeToken<List<String>>() {}.type
        return gson.fromJson(locationJson,type)
    }
}