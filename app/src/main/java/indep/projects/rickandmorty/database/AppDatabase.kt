package indep.projects.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import indep.projects.rickandmorty.database.converters.EpisodeTypeConverter
import indep.projects.rickandmorty.database.converters.LocationTypeConverter
import indep.projects.rickandmorty.database.converters.OriginTypeConverter
import indep.projects.rickandmorty.network.model.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(LocationTypeConverter::class, OriginTypeConverter::class, EpisodeTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}