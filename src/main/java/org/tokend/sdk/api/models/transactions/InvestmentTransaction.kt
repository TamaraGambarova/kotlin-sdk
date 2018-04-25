package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.Offer
import org.tokend.sdk.api.models.Participant
import org.tokend.sdk.api.models.PaymentRecord
import java.math.BigDecimal
import java.math.MathContext

/**
 * Created by Oleg Koretsky on 1/4/18.
 */
class InvestmentTransaction(amount: String?,
                            asset: String?,
                            val quoteAmount: String?,
                            val quoteAsset: String?,
                            val price: String?,
                            val isIncome: Boolean,
                            createdAt: String?,
                            paymentFee: String?,
                            type: String?,
                            state: PaymentState?,
                            id: String?,
                            pagingToken: String?,
                            participants: List<Participant>?,
                            toBalance: String?,
                            toAccount: String?) :
        Transaction(amount = amount,
                assetString = asset,
                createdAt = createdAt,
                type = type,
                state = state,
                id = id,
                pagingToken = pagingToken,
                fromBalance = null,
                toBalance = toBalance,
                to = toAccount,
                participants = participants,
                sourcePaymentFee = paymentFee) {


    override fun isReceived(accountId: String) = isIncome

    companion object {
        const val TYPE_INVESTMENT = "check_sale_state"

        fun fromRecord(record: PaymentRecord, accountId: String, accountAssetBalanceId: String, contextAsset: String)
                : List<InvestmentTransaction> {
            val ourParticipant = record.participants?.find {
                it.accountId == accountId && it.balanceId == accountAssetBalanceId
            }

            return ourParticipant?.effects?.map { effect ->
                val matches = effect.matches

                val baseAsset = effect.baseAsset
                val quoteAsset = effect.quoteAsset

                val quoteAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.quoteAmount)
                })
                val baseAmount = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.baseAmount)
                })
                val fee = matches?.fold(BigDecimal.ZERO, { acc, item ->
                    acc.add(item.fee)
                })?.toPlainString()

                val price =
                        if (baseAmount == null || baseAmount.signum() == 0)
                            null
                        else quoteAmount?.divide(baseAmount, MathContext.DECIMAL128)

                val contextAssetIsQuote = contextAsset == quoteAsset

                InvestmentTransaction(
                        amount = if (contextAssetIsQuote) quoteAmount?.toPlainString() else baseAmount?.toPlainString(),
                        asset = if (contextAssetIsQuote) quoteAsset else baseAsset,
                        quoteAmount = if (contextAssetIsQuote) baseAmount?.toPlainString() else quoteAmount?.toPlainString(),
                        quoteAsset = if (contextAssetIsQuote) baseAsset else quoteAsset,
                        isIncome = !contextAssetIsQuote,
                        price = price?.toPlainString(),
                        createdAt = record.ledgerCloseTime,
                        type = record.type,
                        state = PaymentState.fromCode(record.state),
                        id = record.id,
                        pagingToken = record.pagingToken,
                        participants = record.participants,
                        paymentFee = fee,
                        toBalance = ourParticipant.balanceId,
                        toAccount = ourParticipant.accountId
                )
            } ?: listOf()
        }

        fun fromOffer(offer: Offer): InvestmentTransaction {
            return InvestmentTransaction(
                    amount = offer.quoteAmount,
                    asset = offer.quoteAsset,
                    quoteAsset = offer.baseAsset,
                    quoteAmount = offer.baseAmount,
                    price = offer.price,
                    isIncome = false,
                    createdAt = offer.createdAt,
                    pagingToken = offer.pagingToken,
                    state = PaymentState.SUCCESS,
                    id = offer.id,
                    type = TYPE_INVESTMENT,
                    toBalance = offer.baseBalance,
                    paymentFee = offer.fee,
                    toAccount = null,
                    participants = null
            )
        }
    }
}