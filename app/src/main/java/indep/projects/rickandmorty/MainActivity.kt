package indep.projects.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import indep.projects.rickandmorty.feed.RickAndMortyFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val characterFragment = RickAndMortyFragment()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container, characterFragment,
                RickAndMortyFragment.TAG
            )
            .commit()
    }
}
