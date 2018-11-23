package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Query params for withdrawal reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-withdrawals-requests">Docs</a>
 */
class WithdrawalRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null,
        val destAsset: String? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            destAsset?.also { put("dest_asset_code", it) }
        }
    }
}