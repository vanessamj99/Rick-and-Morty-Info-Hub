package indep.projects.rickandmorty.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import indep.projects.rickandmorty.network.model.Origin

class OriginTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromOrigin(location: Origin?): String? {
        return gson.toJson(location)
    }
    @TypeConverter
    fun toOrigin(locationJson: String?): Origin? {
        if(locationJson == null){
            return null
        }
        val type = object: TypeToken<Origin>() {}.type
        return gson.fromJson(locationJson,type)
    }
}