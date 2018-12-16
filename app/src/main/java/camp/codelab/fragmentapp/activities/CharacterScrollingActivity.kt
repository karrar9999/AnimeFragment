package camp.codelab.fragmentapp.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.models.Anime
import camp.codelab.fragmentapp.models.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_scrolling.*
import kotlinx.android.synthetic.main.item_character.*

class CharacterScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_scrolling)
        setSupportActionBar(toolbar)


        val character=intent.getParcelableExtra<Character>("ANIME")
        toolbar_layout.title=character.name


        fab.setOnClickListener { view ->

        }
    }
}
