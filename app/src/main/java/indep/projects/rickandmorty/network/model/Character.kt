package indep.projects.rickandmorty.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin?,
    val location: Location?,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
