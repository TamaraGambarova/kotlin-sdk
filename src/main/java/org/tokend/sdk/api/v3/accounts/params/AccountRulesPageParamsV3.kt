package org.tokend.sdk.api.v3.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

open class AccountRulesPageParamsV3(
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    open class Builder : PageQueryParams.Builder() {

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AccountRulesPageParamsV3 {
            return AccountRulesPageParamsV3(include, pagingParams)
        }
    }
}