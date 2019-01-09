package org.tokend.sdk.api.v2.balances

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.balances.model.BalanceResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BalancesService {
    @GET("balances")
    @JvmSuppressWildcards
    fun getBalances(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BalanceResource>>>

    @GET("balances/{id}")
    @JvmSuppressWildcards
    fun getBalanceById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<BalanceResource>>
}