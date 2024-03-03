package com.takseha.data.api

import com.takseha.data.BuildConfig
import com.takseha.data.dto.LoginPageResponse
import com.takseha.data.dto.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitudyApi {
    @GET(BuildConfig.LOGIN_PAGE_API)
    suspend fun getLoginPage(): LoginPageResponse

    @GET(BuildConfig.LOGIN_API)
    suspend fun getAllTokens(@Path("platformType") platformType: String, @Query("code") code: String, @Query("state") state: String): LoginResponse
}