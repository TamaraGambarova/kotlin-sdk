package org.tokend.sdk.api.base.model.operations

import java.math.BigDecimal

open class MatchData @JvmOverloads constructor(
        val quoteAsset: String,
        val quoteAmount: BigDecimal,
        /**
         * 1 BASE = price * 1 QUOTE.
         * For example, 1 BTC = 6,583 * 1 USD
         */
        val price: BigDecimal,
        val isBuy: Boolean,
        val orderId: Long?,
        val orderBookId: Long? = 0L
)