package com.dongnaoedu.flowpractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class SearchResultEntity(
    var totalcount: Int,
    var pagecount: Int,
    var bookinfo: List<SearchItem>,
    var authors: List<String>
) : Parcelable{
    @Parcelize
    class SearchItem(
        var bid: String,
        var catename: String? = null,
        var authorname: String? = null,
    ) : Parcelable {

    }
}

