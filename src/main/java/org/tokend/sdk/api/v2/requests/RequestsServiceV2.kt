package org.tokend.sdk.api.v2.requests

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RequestsServiceV2 {
    @GET("requests")
    @JvmSuppressWildcards
    fun getRequests(@QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<List<ReviewableRequestResource>>>

    @GET("requests/{id}")
    @JvmSuppressWildcards
    fun getRequestById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<ReviewableRequestResource>>
}