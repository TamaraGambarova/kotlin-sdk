package org.tokend.sdk.api.base.model

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.params.PagingParamsV2
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Light-weight version of the [Page].
 */
class DataPage<T>(val nextCursor: String?,
                  val items: List<T>,
                  val isLast: Boolean = false) {
    companion object {
        fun <T> getNextCursor(page: Page<T>): String? {
            val nextLink = page.getLinks()?.next?.href ?: return null
            return getNumberParamFromLink(nextLink, "cursor")
                    ?: getNumberParamFromLink(nextLink, "page")
        }

        private fun getNumberParamFromLink(link: String, param: String): String? {
            return "${Pattern.quote(param)}=(\\d+)"
                    .toRegex()
                    .find(link)
                    ?.groups
                    ?.lastOrNull()
                    ?.value
        }

        fun <T> isLast(page: Page<T>): Boolean {
            val selfLink = page.getLinks()?.self?.href ?: return true
            val limit =
                    getNumberParamFromLink(selfLink, "limit")?.toIntOrNull() ?: return true
            return page.records.size < limit
        }

        fun <T> fromPage(page: Page<T>): DataPage<T> {
            return DataPage(getNextCursor(page), page.records, isLast(page))
        }

        /**
         * Creates [DataPage] from collection [JSONAPIDocument] with specific meta info.
         */
        fun <T> fromPageDocument(pageDocument: JSONAPIDocument<List<T>>): DataPage<T> {
            val selfLink = URLDecoder.decode(pageDocument.links.self.href, "UTF-8")
            val nextLink = pageDocument.links?.next?.href?.let {
                URLDecoder.decode(it, "UTF-8")
            }

            val nextCursor =
                    getNumberParamFromLink(nextLink
                            ?: selfLink, PagingParamsV2.QUERY_PARAM_PAGE_NUMBER)

            val limit = getNumberParamFromLink(nextLink
                    ?: selfLink, PagingParamsV2.QUERY_PARAM_LIMIT)
                    ?.toIntOrNull()
                    ?: 0

            val items = pageDocument.get()
            val isLast = nextLink == null || items.size < limit

            return DataPage(nextCursor, items, isLast)
        }
    }
}