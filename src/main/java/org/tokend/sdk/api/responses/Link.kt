package org.tokend.sdk.api.responses

import com.google.gson.annotations.SerializedName

import java.net.URI
import java.net.URISyntaxException

/**
 * Represents links in responses.
 */
class Link internal constructor(@field:SerializedName("href")
                                val href: String,
                                @field:SerializedName("templated")
                                val isTemplated: Boolean) {
    val uri: URI
        get() {
            try {
                return URI(href)
            } catch (e: URISyntaxException) {
                throw RuntimeException(e)
            }

        }
}
