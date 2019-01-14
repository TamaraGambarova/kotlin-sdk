package org.tokend.sdk.api.v2.balances.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams

/**
 * @see BalanceParams.Includes
 */
open class BalancesPageParams(
        val asset: String? = null,
        val account: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { put("asset", it) }
            account?.also { put("account", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var asset: String? = null
        private var account: String? = null

        fun withAsset(asset: String) = also { this.asset = asset }

        fun withAccount(account: String) = also { this.account = account }

        override fun build(): BalancesPageParams {
            return BalancesPageParams(asset, account, include, pagingParams)
        }
    }
}