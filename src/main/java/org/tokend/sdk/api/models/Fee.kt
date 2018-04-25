package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Created by Oleg Koretsky on 11/28/17.
 */
data class Fee(@SerializedName("fee_type") val feeType: Int,
          @SerializedName("asset") val asset: String,
          @SerializedName("fixed") private val fixedString: String,
          @SerializedName("percent") private val percentString: String,
          @SerializedName("lower_bound") private val lowBoundString: String) {

    val fixed: BigDecimal
        get() = BigDecimalUtil.valueOf(fixedString)
    val percent: BigDecimal
        get() = BigDecimalUtil.valueOf(percentString)
    val lowerBound: BigDecimal
        get() = BigDecimalUtil.valueOf(lowBoundString)
}