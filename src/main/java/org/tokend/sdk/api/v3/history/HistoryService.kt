package org.tokend.sdk.api.v3.history

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.ParticipantEffectResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HistoryService {
    @GET("v3/history")
    @JvmSuppressWildcards
    fun getHistory(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ParticipantEffectResource>>>

    @GET("v3/movements")
    @JvmSuppressWildcards
    fun getMovements(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ParticipantEffectResource>>>
}