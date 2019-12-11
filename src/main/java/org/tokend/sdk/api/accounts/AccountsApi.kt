package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.Account
import org.tokend.sdk.api.accounts.model.CreateAccountRequestBody
import org.tokend.sdk.api.accounts.model.SimpleBalanceDetails
import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.api.accounts.params.OffersParams
import org.tokend.sdk.api.accounts.params.PaymentsParams
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.operations.TransferOperation
import org.tokend.sdk.api.base.model.operations.converter.DefaultUnifiedOperationRecordConverter
import org.tokend.sdk.api.base.model.operations.converter.UnifiedOperationRecordConverter
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.keyserver.models.SignerData

open class AccountsApi(
        protected open val accountsService: AccountsService
) {
    /**
     * Will return specific account by ID.
     * @see <a href="https://tokend.gitlab.io/docs/?kotlin#get-account-by-id">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.accounts.AccountsApiV3.getById
     * */
    @Deprecated("We are going to replace with AccountsApiV3.getById")
    open fun getById(accountId: String): ApiRequest<Account> {
        return SimpleRetrofitApiRequest(
                accountsService.getAccount(accountId)
        )
    }

    /**
     * Will return account balances.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-account-balances">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.accounts.AccountsApiV3.getBalances
     */
    @Deprecated("We are going to replace with AccountsApiV3.getBalances")
    open fun getBalances(accountId: String): ApiRequest<List<Account.Balance>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalances(accountId)
        )
    }

    /**
     * Will return extended account balances details.
     * Includes asset details for every balance and sales created for this asset, if present.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-account-balances-details">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.accounts.AccountsApiV3.getBalances
     */
    @Deprecated("We are going to replace with AccountsApiV3.getBalances")
    open fun getBalancesDetails(accountId: String): ApiRequest<List<SimpleBalanceDetails>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalancesDetails(accountId)
        )
    }

    /**
     * Will return list of account payments represented by [UnifiedOperationRecord].
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-account-payments">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.history.HistoryApi.get
     */
    @Deprecated("We are going to replace with HistoryApi.get")
    open fun getRawPayments(accountId: String,
                            paymentsParams: PaymentsParams? = null
    ): ApiRequest<DataPage<UnifiedOperationRecord>> {
        return MappedRetrofitApiRequest(
                accountsService.getPayments(
                        accountId,
                        paymentsParams?.map() ?: emptyMap()
                ),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of account payments represented by [TransferOperation]s
     * using [DefaultUnifiedOperationRecordConverter].
     * @see getRawPayments()
     * @see TransferOperation
     *
     * @see org.tokend.sdk.api.v3.history.HistoryApi.get
     */
    @Deprecated("We are going to replace with HistoryApi.get")
    open fun getPayments(accountId: String,
                         paymentsParams: PaymentsParams
    ): ApiRequest<DataPage<TransferOperation>> {
        val contextAsset = paymentsParams.asset
                ?: throw IllegalArgumentException(
                        "To convert payment records to transactions " +
                                "'asset' param is required"
                )

        val converter = DefaultUnifiedOperationRecordConverter(accountId, contextAsset)

        return getPayments(accountId, paymentsParams, converter)
    }

    /**
     * Will return list of account payments represented by [TransferOperation]s
     * using [DefaultUnifiedOperationRecordConverter].
     *
     * @param recordConverter custom [UnifiedOperationRecordConverter] implementation
     *
     * @see getRawPayments()
     * @see TransferOperation
     * @see UnifiedOperationRecordConverter
     *
     * @see org.tokend.sdk.api.v3.history.HistoryApi.get
     */
    @Deprecated("We are going to replace with HistoryApi.get")
    open fun getPayments(accountId: String,
                         paymentsParams: PaymentsParams,
                         recordConverter: UnifiedOperationRecordConverter
    ): ApiRequest<DataPage<TransferOperation>> {
        return MappedRetrofitApiRequest(
                accountsService.getPayments(
                        accountId,
                        paymentsParams.map()
                ),
                { page ->
                    DataPage(
                            DataPage.getNextCursor(page),
                            recordConverter.toTransferOperations(page.records),
                            DataPage.isLast(page)
                    )
                }
        )
    }

    /**
     * Will return list of pending offers for specified [Account].
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-account-pending-offers">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.offers.OffersApiV3.get
     */
    @Deprecated("We are going to replace with OffersApiV3.get")
    open fun getPendingOffers(accountId: String,
                              offersParams: OffersParams? = null): ApiRequest<DataPage<Offer>> {
        return MappedRetrofitApiRequest(
                accountsService.getPendingOffers(
                        accountId,
                        offersParams?.map()
                ),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Creates an account with specified signers.
     */
    open fun createAccount(accountId: String,
                           signers: Collection<SignerData>): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                accountsService.createAccount(CreateAccountRequestBody(accountId, signers))
        )
    }
}