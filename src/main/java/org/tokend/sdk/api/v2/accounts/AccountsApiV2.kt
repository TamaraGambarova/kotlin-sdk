package org.tokend.sdk.api.v2.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.AccountResource
import org.tokend.sdk.api.generated.resources.BalanceResource
import org.tokend.sdk.api.v2.accounts.params.AccountParamsV2
import org.tokend.sdk.api.v2.accounts.params.AccountsPageParamsV2

open class AccountsApiV2(
        protected open val accountsService: AccountsServiceV2
) {
    /**
     * @return accounts list page
     *
     * Should be signed by signer of requested account or signer of master account
     *
     */
    open fun get(params: AccountsPageParamsV2?): ApiRequest<DataPage<AccountResource>> {
        return MappedRetrofitApiRequest(
                accountsService.getAccounts(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return account by it's ID
     *
     * Should be signed by signer of requested account or signer of master account
     */
    open fun getById(accountId: String,
                     params: AccountParamsV2?): ApiRequest<AccountResource> {
        return MappedRetrofitApiRequest(
                accountsService.getAccountById(
                        accountId,
                        params.map()
                ),
                JSONAPIDocument<AccountResource>::get
        )
    }

    /**
     * @return balances of an account with given ID
     *
     * Should be signed by signer of requested account or signer of master account,
     * otherwise will be populated only with id and asset
     */
    open fun getBalances(accountId: String): ApiRequest<List<BalanceResource>> {
        return getById(accountId, AccountParamsV2(listOf(AccountParamsV2.Includes.BALANCES)))
                .map { it.balances }
    }
}