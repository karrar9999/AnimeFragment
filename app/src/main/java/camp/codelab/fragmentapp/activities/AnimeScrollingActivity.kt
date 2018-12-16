package camp.codelab.fragmentapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.models.Anime
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_anime_scrolling.*
import kotlinx.android.synthetic.main.content_anime_scrolling.*

class AnimeScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_scrolling)
        setSupportActionBar(toolbar)

        val anime = intent.getParcelableExtra<Anime>("ANIME")

        ratingBar.rating=(anime.score).toFloat()
        ratedTextView.text = anime.rated
        Synopsis.text=anime.synopsis
        toolbar_layout.title = anime.title
        Picasso.get()
            .load(anime.imageUrl).into(animeimageview)
        fab.setOnClickListener { view ->
            val url = anime.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            view.context.startActivity(intent)


        }

    }
}
