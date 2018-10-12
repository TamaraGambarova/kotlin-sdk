package org.tokend.sdk.api.sales

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.sales.params.SalesParams

open class SalesApi(
        protected val salesService: SalesService
) {
    fun get(id: Long): ApiRequest<SimpleSale> {
        return SimpleRetrofitApiRequest(
                salesService.getSale(id)
        )
    }

    fun getAll(params: SalesParams? = null): ApiRequest<DataPage<SimpleSale>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params?.map()),
                { DataPage.fromPage(it) }
        )
    }
}