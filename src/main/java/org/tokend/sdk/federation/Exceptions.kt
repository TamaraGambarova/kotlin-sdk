package org.tokend.sdk.federation

import org.tokend.sdk.api.responses.ServerError
import java.io.IOException

open class ForbiddenException(val type: String, detailMessage: String) : IOException(detailMessage)

class NeedTfaException(val backendId: Long,
                       val backendType: String,
                       val token: String,
                       val keychainData: String,
                       val salt: String,
                       val walletId: String)
    : ForbiddenException("tfa_required", "Need $backendType TFA") {

    companion object {
        private const val TOKEN = "token"
        private const val BACKEND_ID = "factor_id"
        private const val BACKEND_TYPE = "factor_type"
        private const val KEYCHAIN_DATA = "keychain_data"
        private const val SALT = "salt"
        private const val WALLET_ID = "wallet_id"

        @JvmStatic
        fun fromError(error: ServerError): NeedTfaException? {
            if (error.detail?.contains("factor") == true) {
                return NeedTfaException(
                        (error.meta?.get(BACKEND_ID) as? Double)?.toLong() ?: 0L,
                        (error.meta?.get(BACKEND_TYPE) as? String).toString(),
                        (error.meta?.get(TOKEN) as? String).toString(),
                        (error.meta?.get(KEYCHAIN_DATA) as? String).toString(),
                        (error.meta?.get(SALT) as? String).toString(),
                        (error.meta?.get(WALLET_ID) as? String).toString())
            }

            return null
        }
    }
}

class InvalidCredentialsException(val credential: Credential) : Exception() {
    enum class Credential {
        EMAIL, PASSWORD
    }
}
class EmailNotVerifiedException(val walletId: String)
    : ForbiddenException("email_not_verified", "Email is not verified")
class EmailAlreadyTakenException : Exception()