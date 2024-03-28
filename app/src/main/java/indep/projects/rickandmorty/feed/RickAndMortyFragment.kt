package indep.projects.rickandmorty.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import indep.projects.rickandmorty.R
import indep.projects.rickandmorty.RMApplication
import indep.projects.rickandmorty.ViewModelFactory
import javax.inject.Inject

class RickAndMortyFragment : Fragment() {
    companion object {
        const val TAG = "RickAndMortyFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RickAndMortyViewModel>

    private val viewModel : RickAndMortyViewModel by lazy{
        viewModelFactory.get<RickAndMortyViewModel>(
            requireActivity()
        )
    }

    private lateinit var rickAndMortyAdapter: RickAndMortyAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        RMApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_fragment, container, false)

        rickAndMortyAdapter = RickAndMortyAdapter()
        recyclerView = view.findViewById(R.id.characters_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = rickAndMortyAdapter
        }

        observeData()
        viewModel.fetchCharacters()
        Log.d("HERE", "${viewModel.characterData.value}")


        return view
    }

    private fun observeData(){
        viewModel.characterData.observe(viewLifecycleOwner){
            rickAndMortyAdapter.updateList(it)
        }
    }
}
