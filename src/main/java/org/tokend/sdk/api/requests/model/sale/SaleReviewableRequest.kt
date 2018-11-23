package org.tokend.sdk.api.requests.model.sale

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.model.base.AbstractReviewableRequest
import java.util.*

class SaleReviewableRequest<SaleType>(
        id: Long,
        pagingToken: String,
        requestor: String,
        reviewer: String,
        reference: String?,
        rejectReason: String,
        hash: String,
        createdAt: Date,
        updatedAt: Date,
        stateI: Int,
        @SerializedName("details")
        override val details: SaleReviewableRequestDetails<SaleType>
) : AbstractReviewableRequest(id, pagingToken, requestor, reviewer, reference,
        rejectReason, hash, createdAt, updatedAt, stateI)