package org.tokend.sdk.api.base.model.operations.converter

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.api.base.model.operations.*

open class DefaultUnifiedOperationRecordConverter(
        protected val contextAccountId: String,
        protected val contextAsset: String
) : UnifiedOperationRecordConverter {

    override fun toTransferOperations(records: Collection<UnifiedOperationRecord>): List<TransferOperation> {
        return records.map {
            when (OperationType.fromLiteral(it.type)) {
                OperationType.PAYMENT ->
                    listOf(PaymentOperation.fromUnifiedOperationRecord(it, contextAccountId))
                OperationType.ISSUANCE ->
                    listOf(IssuanceOperation.fromUnifiedOperationRecord(it, contextAccountId))
                OperationType.WITHDRAWAL ->
                    listOf(WithdrawalOperation.fromUnifiedOperationRecord(it, contextAccountId))
                OperationType.OFFER_MATCH ->
                    OfferMatchOperation.fromUnifiedOperationRecord(it, contextAsset, contextAccountId)
                OperationType.INVESTMENT ->
                    InvestmentOperation.fromUnifiedOperationRecord(it, contextAsset, contextAccountId)
                else ->
                    listOf(BaseTransferOperation.fromPaymentRecord(it, contextAccountId))
            }
        }.flatten()
    }
}