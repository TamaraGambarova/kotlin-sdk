package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.ApiDateUtil
import java.math.BigDecimal
import java.util.*

/**
 * Created by Oleg Koretsky on 1/8/18.
 */
data class AssetChartData(
        @SerializedName("day") val day: List<ChartPoint>? = null,
        @SerializedName("hour") val hour: List<ChartPoint>? = null,
        @SerializedName("month") val month: List<ChartPoint>? = null,
        @SerializedName("year") val year: List<ChartPoint>? = null) {

    class ChartPoint(@SerializedName("value") val value: BigDecimal? = null,
                     @SerializedName("timestamp") private val timestamp: String? = null) {

        private var parsedDate: Date? = null
        val date: Date
            get() {
                val parsedDate = parsedDate
                return if (parsedDate == null) {
                    val date = ApiDateUtil.tryParseDate(this.timestamp)
                    this.parsedDate = date
                    date
                } else {
                    parsedDate
                }
            }
    }
}