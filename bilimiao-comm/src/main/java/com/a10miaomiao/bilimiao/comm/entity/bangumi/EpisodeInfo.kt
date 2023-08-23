package com.a10miaomiao.bilimiao.comm.entity.bangumi

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeInfo(
    val aid: String,
    val attr: Int,
    @SerializedName("badge") val badgeText: String,
    @SerializedName("badge_type") val badgeType: Int,
    val bvid: String,
    val badge: String,
    val badge_type: Int,
    val badge_info: EpisodeBadgeInfo,
    val cid: String,
    val cover: String,
    val ctime: String,
    val duration: Long,
    @SerializedName("ep_id") val epId: String,
    @SerializedName("episode_status") val episodeStatus: Int,
    @SerializedName("episode_type") val episodeType: Int,
    val from: String,
    val index: String,
    @SerializedName("long_title") val longTitle: String? = null,
    @SerializedName("index_title") val indexTitle: String?,
    val mid: Int,
    val page: Int,
    val premiere: Boolean,
    @SerializedName("pub_real_time") val pubRealTime: String,
    @SerializedName("section_id") val sectionId: Int,
    @SerializedName("section_type") val sectionType: Int,
    @SerializedName("share_url") val shareUrl: String,
    val vid: String,
    val dimension: DimensionXInfo? = null,
    /**
     * 就是ep id
     */
    val id: String? = null,
    val status: Int? = null,
    val title: String? = null,
    val pub_real_time: String,
    val section_id: String,
    val section_type: Int,
    val share_url: String,
) : Parcelable {

    val safeTitle get() = longTitle?.takeIf { it.isNotEmpty() } ?: title ?: indexTitle ?: "-"

    @Parcelize
    data class EpisodeBadgeInfo(
        val bg_color: String,
        val bg_color_night: String,
        val text: String,
    ) : Parcelable
}
