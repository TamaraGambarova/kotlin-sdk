package org.tokend.sdk.api.sales

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.sales.model.SaleAnte
import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.sales.params.AntesParams
import org.tokend.sdk.api.sales.params.SalesParams

open class SalesApi(
        protected val salesService: SalesService
) {
    /**
     * Will return detailed information about the sale with specified ID.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-sale-by-id">Docs</a>
     */
    open fun getById(id: Long): ApiRequest<SimpleSale> {
        return SimpleRetrofitApiRequest(
                salesService.getSale(id)
        )
    }

    /**
     * Will return sales that match specified filters.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-sales">Docs</a>
     */
    open fun getAll(params: SalesParams? = null): ApiRequest<DataPage<SimpleSale>> {
        return MappedRetrofitApiRequest(
                salesService.getSales(params?.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of charged antes.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-sale-antes">Docs</a>
     */
    open fun getAntes(params: AntesParams? = null): ApiRequest<DataPage<SaleAnte>> {
        return MappedRetrofitApiRequest(
                salesService.getSaleAntes(params?.map()),
                { DataPage.fromPage(it) }
        )
    }
}