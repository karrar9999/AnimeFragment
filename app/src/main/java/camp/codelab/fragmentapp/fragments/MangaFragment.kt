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
import camp.codelab.fragmentapp.adapters.MangaAdapter
import camp.codelab.fragmentapp.models.Manga
import camp.codelab.fragmentapp.models.MangaSearchResponse
import camp.codelab.fragmentapp.retrofit.SearchInterface
import camp.codelab.fragmentapp.utils.Consts
import kotlinx.android.synthetic.main.fragment_manga.*
import camp.codelab.fragmentapp.utils.setTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MangaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_manga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchMangaEditText.setTextChangedListener {

            search(it)
        }


    }

    private fun search(searchQuery: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val searchInterface = retrofit.create(SearchInterface::class.java)
        progressBar2.visible()
        searchInterface.searchManga(searchQuery)
            .enqueue(object : Callback<MangaSearchResponse> {
                override fun onFailure(call: Call<MangaSearchResponse>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<MangaSearchResponse>, response: Response<MangaSearchResponse>) {
                    response.body()?.results?.let {
                        prepareRecyclerView(it)
                        progressBar2.gone()
                    }
                }

            })


    }

    fun prepareRecyclerView(managaList: List<Manga>) {
        mangaRecycleView.layoutManager = GridLayoutManager(activity, 2)
        mangaRecycleView.adapter = MangaAdapter(managaList)
    }

}




