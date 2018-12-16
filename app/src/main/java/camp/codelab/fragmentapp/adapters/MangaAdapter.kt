package camp.codelab.fragmentapp.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.activities.MangaScrollingActivity
import camp.codelab.fragmentapp.adapters.MangaAdapter.MangaViewHolder
import camp.codelab.fragmentapp.models.Manga
import camp.codelab.fragmentapp.utils.loadImageFromURL
import kotlinx.android.synthetic.main.item_manga.view.*

class MangaAdapter(val mangaList: List<Manga>) : RecyclerView.Adapter<MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manga,parent, false)
        return MangaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mangaList.size
    }

    override fun onBindViewHolder(viewHolder: MangaViewHolder, position: Int) {
        viewHolder.setManga(mangaList[position])
    }


    inner class MangaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        init {

            view.setOnClickListener {
                val intent = Intent(it.context, MangaScrollingActivity::class.java)
                intent.putExtra("MANGA", mangaList[layoutPosition])
                it.context.startActivity(intent)
            }

        }
        fun setManga(manga: Manga) {

            view.mangaTitleTextView.text = manga.title
            view.mangaimageview.loadImageFromURL(manga.imageUrl)

        }

    }
}
