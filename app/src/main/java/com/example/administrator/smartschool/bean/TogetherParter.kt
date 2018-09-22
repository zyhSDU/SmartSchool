package com.example.administrator.smartschool.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2018/9/22 0022.
 */

class TogetherParter() : Parcelable {
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(toId)
        dest.writeInt(id)
        dest.writeInt(userId)
        dest.writeString(info)
    }

    override fun describeContents(): Int {
        return  0
    }

    var toId: Int = 0
    var id: Int = 0
    var userId: Int = 0
    var info: String? = null

    constructor(parcel: Parcel) : this() {
        toId = parcel.readInt()
        id = parcel.readInt()
        userId = parcel.readInt()
        info = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<TogetherParter> {
        override fun createFromParcel(parcel: Parcel): TogetherParter {
            return TogetherParter(parcel)
        }

        override fun newArray(size: Int): Array<TogetherParter?> {
            return arrayOfNulls(size)
        }
    }
}