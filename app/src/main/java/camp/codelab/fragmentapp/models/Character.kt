package camp.codelab.fragmentapp.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Character() : Parcelable {

    @SerializedName("mal_id")
    var malid: Int = 0
    @SerializedName("url")
    var url: String = ""
    @SerializedName("image_url")
    var imageurl: String = ""
    @SerializedName("name")
    var name: String =""

    constructor(parcel: Parcel) : this() {
        malid = parcel.readInt()
        url = parcel.readString()
        imageurl = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(malid)
        parcel.writeString(url)
        parcel.writeString(imageurl)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }


}