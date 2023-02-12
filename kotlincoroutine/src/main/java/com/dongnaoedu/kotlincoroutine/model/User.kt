package com.dongnaoedu.kotlincoroutine.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class User(
    @PrimaryKey
    var bid: String,
    var status: Int= 0,
    var chpid: String? = null,
    var share_times : Long = 0,
    var intro: String? = null,
    var catename: String? = null,
    var classid: String? = null,
    var classid2: String? = null,
    var author: String? = null,
    var posttime: String? = null,
    var firstchar: String? = null,
    var lzinfo: String? = null,
    var authorid: String? = null,
    var shouquaninfo: String? = null,
    var charnum: String? = null,
    var salenum: String? = null,
    var note: String? = null,
    var viptime: String? = null,
    var publishstatus: String? = null,
    var tags: String? = null,
    var keywords: String? = null,
    var total_hit: String? = null,
    var total_fav: String? = null,
    var month_hit: String? = null,
    var star: String? = null,
    var last_updatetime: String? = null,
    var last_updatechpid: String? = null,
    var last_updatechptitle: String? = null,
    var last_updatejuanid: String? = null,
    var last_vipupdatechpid: String? = null,
    var last_vipupdatejuanid: String? = null,
    var last_vipupdatechptitle: String? = null,
    var imgstatus: String? = null,
    var booktype: String? = null,
    var wanjie_time: String? = null,
    var sourceid: String? = null,
    var copyright: String? = null,
    var systag: String? = null,
    var classname: String? = null,
    var isvip: Int= 0,
    var sex_flag: String? = null,
    var cover: String? = null,
    var total_comment: String? = null,
    var level: String? = null,
    var tagsary: List<String>? = null,
//  var show_section: List<ShowSectionBean>? = null,
    var action_section: List<ActionSectionBean>? = null,
    var juheid: String? = null,
    var juheclassname: String? = null
) {
    @Parcelize
    class ActionSectionBean(
        /**
         * flag : download
         * title : 下载
         * icon :
         * action : download
         * target :
         */
        var flag: String? = null,
        var title: String? = null,
        var icon: String? = null,
        var action: String? = null,
        var target: String? = null,
    ) : Parcelable {

    }
}