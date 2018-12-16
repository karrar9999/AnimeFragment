package camp.codelab.fragmentapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.models.Manga
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_manga_scrolling.*
import kotlinx.android.synthetic.main.content_manga_scrolling.*
import kotlinx.android.synthetic.main.item_manga.*


class MangaScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga_scrolling)
        setSupportActionBar(toolbar)


        val manga = intent.getParcelableExtra<Manga>("MANGA")
        manga_toolbar_layout.title =manga.title
        ratingBar2.rating = (manga.score).toFloat()
        MangatextView3.text = manga.synopsis
        MangaRatedtextView.text = manga.type
        Picasso.get().load(manga.imageUrl).into(mangaimageview)






        fab.setOnClickListener { view ->
            val url = manga.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            view.context.startActivity(intent)
        }
    }
}
