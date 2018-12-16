package camp.codelab.fragmentapp.models

import com.google.gson.annotations.SerializedName


data class MangaSearchResponse(
    @SerializedName("results")
    var results: List<Manga> = listOf()
) : BaseSearchResponse()

