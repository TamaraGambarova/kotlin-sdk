package org.tokend.sdk.api.trades

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.api.trades.params.OrderBookParams

open class TradesApi(
        protected val tradesService: TradesService
) {
    open fun getOrderBook(params: OrderBookParams): ApiRequest<DataPage<Offer>> {
        return MappedRetrofitApiRequest(
                tradesService.getOrderBook(params.map()),
                { DataPage.fromPage(it) }
        )
    }
}