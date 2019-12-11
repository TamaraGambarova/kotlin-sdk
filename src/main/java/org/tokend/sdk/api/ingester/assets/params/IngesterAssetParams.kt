package org.tokend.sdk.api.ingester.assets.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
open class IngesterAssetParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val OWNER = "owner"
        }
    }
}