package org.tokend.sdk.api.ingester.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.ingester.generated.resources.TransactionResource
import org.tokend.sdk.api.ingester.transactions.model.SubmitTransactionRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IngesterTransactionsService {
    @POST("horizon/transactions")
    @JvmSuppressWildcards
    fun submit(@Body body: SubmitTransactionRequestBody): Call<JSONAPIDocument<TransactionResource>>
}