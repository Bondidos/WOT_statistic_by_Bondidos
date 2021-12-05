package com.bondidos.wotstatisticbybondidos.data.sources.api

import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val FUNCTION_SEARCH = "/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167"

interface WotApi {

   /* @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=LegitimateKiller")
    suspend fun searchUser() : SearchResponse

    @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=legitim")
    fun getStatus():Response<String>

    @GET("/wot/account/achievements/")
    suspend fun getUserAchieves(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int
        ): AchievesResponse*/

    @GET("/wot/account/info/")
    suspend fun getUserData(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int,
        @Query("extra") extra: String?,
        @Query("access_token") access_token: String,
        @Query("fields") fields: String?
    ): Response<ResponseBody>



    /*@GET("/wot/account/achievements/?application_id=5d489c586717c2b76ade8bea16607167&account_id=560508396")
    suspend fun getUserAchieves(): AchievesResponse*/
}