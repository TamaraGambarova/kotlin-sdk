package org.tokend.sdk.api.v3.sales

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.SaleResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SalesServiceV3 {
    @GET("v3/sales")
    @JvmSuppressWildcards
    fun getSales(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SaleResource>>>

    @GET("v3/sales/{id}")
    @JvmSuppressWildcards
    fun getSaleById(@Path("id") id: String,
                    @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SaleResource>>
}