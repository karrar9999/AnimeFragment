package camp.codelab.fragmentapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun EditText.setTextChangedListener(callback: (text: String) -> Unit) {

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            callback(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })

}

fun ImageView.loadImageFromURL(url: String) {

    Picasso.get()
        .load(url)
        .into(this)

}