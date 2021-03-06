package org.tokend.sdk.api.integrations.cards.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity

class CreateCardRequest(
        val number: String,
        ownerAccountId: String,
        details: Any?,
        balances: Collection<String>
) {
    @SerializedName("attributes")
    val attributes = mapOf(
            "card_number" to number,
            "details" to details
    )

    @SerializedName("relationships")
    val relationships = mapOf(
            "owner" to DataEntity(mapOf("id" to ownerAccountId)),
            "balances" to DataEntity(balances.map {
                mapOf("id" to it)
            }),
            "security_details" to DataEntity(mapOf("id" to "1", "type" to "cards-security-details"))
    )
}