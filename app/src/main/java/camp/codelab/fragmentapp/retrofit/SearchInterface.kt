package camp.codelab.fragmentapp.retrofit

import camp.codelab.fragmentapp.models.Anime
import camp.codelab.fragmentapp.models.AnimeSearchResponse
import camp.codelab.fragmentapp.models.CharacterSearchResponse
import camp.codelab.fragmentapp.models.MangaSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchInterface {


    @GET("search/anime")
    fun searchAnime(@Query("q") searchQuery: String): Call<AnimeSearchResponse>


    @GET("search/manga")
    fun searchManga(@Query("q") searchQuery: String): Call<MangaSearchResponse>


    @GET("search/character")
    fun searchCharacter(@Query("q") searchQuery: String): Call<CharacterSearchResponse>




    @GET("search/anime")
    fun getAnimeInfo(
        @Query("i") id: Int
    ): Call<Anime>


}