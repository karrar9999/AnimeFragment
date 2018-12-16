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
import camp.codelab.fragmentapp.adapters.CharacterAdapter
import camp.codelab.fragmentapp.models.Character
import camp.codelab.fragmentapp.models.CharacterSearchResponse
import camp.codelab.fragmentapp.retrofit.SearchInterface
import camp.codelab.fragmentapp.utils.Consts
import camp.codelab.fragmentapp.utils.setTextChangedListener
import kotlinx.android.synthetic.main.fragment_anime.*
import kotlinx.android.synthetic.main.fragment_character.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchCharacterEditText.setTextChangedListener {
            search(it)

        }
    }

    private fun search(searchQuery: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val searchInterface = retrofit.create(SearchInterface::class.java)
        progressBar3.visible()
        searchInterface.searchCharacter(searchQuery)
            .enqueue(object : Callback<CharacterSearchResponse> {
                override fun onFailure(call: Call<CharacterSearchResponse>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<CharacterSearchResponse>,
                    response: Response<CharacterSearchResponse>
                ) {
                    response.body()?.results?.let {
                        prepareRecyclerView(it)
                        progressBar3.gone()
                    }
                }

            })
    }

    private fun prepareRecyclerView(characterList: List<Character>) {
        recyclerView2.layoutManager = GridLayoutManager(activity, 2)
        recyclerView2.adapter = CharacterAdapter(characterList)
    }
}




