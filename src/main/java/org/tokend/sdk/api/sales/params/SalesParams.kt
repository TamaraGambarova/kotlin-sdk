package org.tokend.sdk.api.sales.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.QueryParams

open class SalesParams(
        val pagingParams: PagingParams? = null,
        val name: String? = null,
        val baseAsset: String? = null,
        val ownerAccountId: String? = null,
        val openOnly: Boolean? = null,
        val upcoming: Boolean? = null,
        val voting: Boolean? = null,
        val promotions: Boolean? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            pagingParams?.also { putAll(it.map()) }
            name?.also { put("name", it) }
            baseAsset?.also { put("base_asset", it) }
            ownerAccountId?.also { put("owner", it) }
            openOnly?.also { put("open_only", it) }
            upcoming?.also { put("upcoming", it) }
            voting?.also { put("voting", it) }
            promotions?.also { put("promotions", it) }
        }
    }
}