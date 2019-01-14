package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName

open class TfaFactor(
        @SerializedName("id")
        val id: Long,
        @SerializedName("type")
        val type: Type,
        @SerializedName("attributes")
        val attributes: Attributes
) {
    enum class Type(val literal: String) {
        @SerializedName("totp")
        TOTP("totp"),
        @SerializedName("password")
        PASSWORD("password"),
        @SerializedName("email")
        EMAIL("email"),
        UNKNOWN("unknown");

        companion object {
            @JvmStatic
            fun fromLiteral(literal: String?): Type {
                return values().find { it.literal == literal } ?: UNKNOWN
            }
        }
    }

    open class Attributes(
            @SerializedName("priority")
            val priority: Int
    )

    override fun equals(other: Any?): Boolean {
        return other is TfaFactor
                && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}