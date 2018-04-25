package org.tokend.sdk.api.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String

//Todo: test me
data class KeychainData(@SerializedName("IV")
                        private val rawIV: String,
                        @SerializedName("cipherText")
                        private val rawCipherText: String) {

    val iv: ByteArray
        get() = rawIV.decodeBase64()

    val cipherText: ByteArray
        get() = rawCipherText.decodeBase64()

    companion object {
        fun fromDecoded(decodedIV: ByteArray, decodedCipherText: ByteArray): KeychainData {
            return KeychainData(decodedIV.encodeBase64String(),
                    decodedCipherText.encodeBase64String())
        }

        fun fromJson(rawJson: String): KeychainData {
            return Gson().fromJson(rawJson, KeychainData::class.java)
        }

        fun fromRawString(rawString: String): KeychainData {
            return fromJson(String(rawString.decodeBase64()))
        }
    }
}
