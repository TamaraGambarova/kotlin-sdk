package org.tokend.sdk.api.base.model

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.base.PaginationMeta
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
            val pattern = Pattern.compile("$param=(\\d+)")
            val matcher = pattern.matcher(link)
            return if (matcher.find()) {
                matcher.group(matcher.groupCount())
            } else {
                null
            }
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
            val paginationMeta = PaginationMeta.fromMap(pageDocument.meta)

            return DataPage(
                    items = pageDocument.get(),
                    nextCursor = paginationMeta.currentPage.inc().toString(),
                    isLast = paginationMeta.isLast
            )
        }
    }
}