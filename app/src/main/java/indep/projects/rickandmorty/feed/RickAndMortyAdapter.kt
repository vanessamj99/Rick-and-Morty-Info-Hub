package indep.projects.rickandmorty.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import indep.projects.rickandmorty.R
import indep.projects.rickandmorty.network.model.Character

class RickAndMortyAdapter: RecyclerView.Adapter<StoreItemViewHolder>() {
    private var items = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val fetchedCharacter = items[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = fetchedCharacter.name
            findViewById<TextView>(R.id.species).text = fetchedCharacter.species
            Glide.with(this).load(fetchedCharacter.image).into(findViewById<ImageView>(R.id.picture))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newList: List<Character>){
        val updatingResults = StoreDiffCallback(items, newList)
        val differentResults = DiffUtil.calculateDiff(updatingResults)
        items.clear()
        items.addAll(newList)
        differentResults.dispatchUpdatesTo(this)
    }
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class StoreDiffCallback(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}