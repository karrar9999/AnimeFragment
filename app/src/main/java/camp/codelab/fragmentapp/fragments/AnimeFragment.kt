package camp.codelab.fragmentapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.activities.gone
import camp.codelab.fragmentapp.activities.visible
import camp.codelab.fragmentapp.adapters.AnimeAdapter
import camp.codelab.fragmentapp.models.Anime
import camp.codelab.fragmentapp.models.AnimeSearchResponse
import camp.codelab.fragmentapp.retrofit.SearchInterface
import camp.codelab.fragmentapp.utils.Consts
import camp.codelab.fragmentapp.utils.setTextChangedListener
import kotlinx.android.synthetic.main.fragment_anime.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 *
 */
class AnimeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_anime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAnimeEditText.setTextChangedListener {
            search(it)
        }

    }

    private fun search(searchQuery: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val searchInterface = retrofit.create(SearchInterface::class.java)
        progressBar.visible()
        searchInterface.searchAnime(searchQuery)
            .enqueue(object : Callback<AnimeSearchResponse> {
                override fun onFailure(call: Call<AnimeSearchResponse>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<AnimeSearchResponse>, response: Response<AnimeSearchResponse>) {
                    response.body()?.results?.let {
                        prepareRecyclerView(it)
                        progressBar.gone()

                    }
                }

            })
    }

    private fun prepareRecyclerView(animeList: List<Anime>) {
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = AnimeAdapter(animeList)
    }

}
