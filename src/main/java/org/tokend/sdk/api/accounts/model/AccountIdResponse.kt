package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName

data class AccountIdResponse(@SerializedName("account_id") val accountId: String)