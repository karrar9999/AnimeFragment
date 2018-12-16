package camp.codelab.fragmentapp.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import camp.codelab.fragmentapp.R
import camp.codelab.fragmentapp.activities.CharacterScrollingActivity
import camp.codelab.fragmentapp.models.Character
import camp.codelab.fragmentapp.utils.loadImageFromURL
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(val characterList: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, posstion: Int) {
        viewHolder.setCharacter(characterList[posstion])
    }

    inner class CharacterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {

            view.setOnClickListener {

                val intent = Intent(it.context, CharacterScrollingActivity::class.java)
                intent.putExtra("CHARACTER", characterList[layoutPosition])
                it.context.startActivity(intent)
            }
        }


        fun setCharacter(character: Character) {
            view.characterTitleTextView.text = character.name
            view.characterimageview.loadImageFromURL(character.imageurl)


        }


    }
}




