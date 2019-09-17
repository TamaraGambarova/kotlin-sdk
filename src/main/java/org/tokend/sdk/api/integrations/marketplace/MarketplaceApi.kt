package org.tokend.sdk.api.integrations.marketplace

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceBuyRequestAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceOfferResource
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOfferParams
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOffersPageParams
import org.tokend.sdk.factory.GsonFactory

open class MarketplaceApi(
        protected open val marketplaceService: MarketplaceService
) {
    open fun submitBuyRequest(request: MarketplaceBuyRequestAttributes): ApiRequest<MarketplaceInvoiceData> {
        return MappedRetrofitApiRequest(
                marketplaceService.submitBuyRequest(DataEntity(AttributesEntity(request))),
                { getInvoiceData(it.data.attributes) }
        )
    }

    @JvmOverloads
    open fun getOffers(params: MarketplaceOffersPageParams? = null)
            : ApiRequest<DataPage<MarketplaceOfferResource>> {
        return MappedRetrofitApiRequest(
                marketplaceService.getOffersPage(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getOffer(id: String,
                      params: MarketplaceOfferParams? = null): ApiRequest<MarketplaceOfferResource> {
        return MappedRetrofitApiRequest(
                marketplaceService.getOffer(
                        id,
                        params.map()
                ),
                JSONAPIDocument<MarketplaceOfferResource>::get
        )
    }

    protected open fun getInvoiceData(attributes: MarketplaceInvoiceAttributes): MarketplaceInvoiceData {
        val gson = GsonFactory().getBaseGson()

        return when (attributes.type) {
            MarketplaceInvoiceData.Crypto.TYPE ->
                gson.fromJson(attributes.data, MarketplaceInvoiceData.Crypto::class.java)
            MarketplaceInvoiceData.Redirect.TYPE ->
                gson.fromJson(attributes.data, MarketplaceInvoiceData.Redirect::class.java)
            else -> throw IllegalArgumentException("Unknown marketplace invoice data type '${attributes.type}'")
        }
    }
}