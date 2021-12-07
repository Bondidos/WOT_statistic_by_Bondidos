package com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi


import com.google.gson.annotations.SerializedName

data class X500002997(
    @SerializedName("accepts_join_requests")
    val acceptsJoinRequests: Boolean?,
    @SerializedName("clan_id")
    val clanId: Int?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("created_at")
    val createdAt: Int?,
    @SerializedName("creator_id")
    val creatorId: Int?,
    @SerializedName("creator_name")
    val creatorName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_html")
    val descriptionHtml: String?,
    @SerializedName("emblems")
    val emblems: Emblems?,
    @SerializedName("is_clan_disbanded")
    val isClanDisbanded: Boolean?,
    @SerializedName("leader_id")
    val leaderId: Int?,
    @SerializedName("leader_name")
    val leaderName: String?,
    @SerializedName("members_count")
    val membersCount: Int?,
    @SerializedName("motto")
    val motto: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("old_name")
    val oldName: String?,
    @SerializedName("old_tag")
    val oldTag: String?,
    @SerializedName("private")
    val `private`: Any?,
    @SerializedName("renamed_at")
    val renamedAt: Int?,
    @SerializedName("tag")
    val tag: String?,
    @SerializedName("updated_at")
    val updatedAt: Int?
)