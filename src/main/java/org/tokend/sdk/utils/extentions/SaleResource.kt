package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.resources.SaleResource
import org.tokend.sdk.api.sales.model.SaleState
import java.util.*

fun SaleResource.isAvailable(): Boolean {
    return !isUpcoming() || !isEnded()
}

fun SaleResource.isUpcoming(): Boolean {
    return startTime.after(Date())
}

fun SaleResource.isEnded(): Boolean {
    return isClosed() || isCanceled()
}

fun SaleResource.isClosed(): Boolean {
    return saleState.value == SaleState.CLOSED.value
}

fun SaleResource.isCanceled(): Boolean {
    return saleState.value == SaleState.CANCELED.value
}