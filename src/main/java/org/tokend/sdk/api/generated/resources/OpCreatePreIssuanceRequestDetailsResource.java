// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-create-preissuance-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreatePreIssuanceRequestDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}