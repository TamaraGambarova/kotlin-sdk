package org.tokend.sdk.api.requests.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.tfa.TfaBackend

open class UpdateTfaRequestBody(@SerializedName("attributes") val attributes: TfaBackend.Attributes)