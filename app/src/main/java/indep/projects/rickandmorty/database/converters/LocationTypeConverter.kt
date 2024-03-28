package indep.projects.rickandmorty.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import indep.projects.rickandmorty.network.model.Location

class LocationTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location?): String? {
        return gson.toJson(location)
    }
    @TypeConverter
    fun toLocation(locationJson: String?): Location? {
        if(locationJson == null){
            return null
        }
        val type = object: TypeToken<Location>() {}.type
        return gson.fromJson(locationJson,type)
    }
}