package org.tokend.sdk.api.v3.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.fees.model.ExactFeeResource
import org.tokend.sdk.api.v3.fees.model.FeeResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

//TODO: use generated FeeResource after docs refactoring
interface FeesServiceV3 {
    @GET("v3/fees")
    @JvmSuppressWildcards
    fun getFees(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<FeeResource>>>

    @GET("v3/fees/calculate")
    fun calculateFee(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<ExactFeeResource>>
}