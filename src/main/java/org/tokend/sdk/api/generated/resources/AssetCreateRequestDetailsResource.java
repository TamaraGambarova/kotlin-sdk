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


@Type("request-details-asset-create")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetCreateRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("asset")
    private String asset;
    
    public String getAsset() {
        return asset;
    }
    
    @JsonProperty("policies")
    private Integer policies;
    
    public Integer getPolicies() {
        return policies;
    }
    
    @JsonProperty("pre_issuance_asset_signer")
    private String preIssuanceAssetSigner;
    
    public String getPreIssuanceAssetSigner() {
        return preIssuanceAssetSigner;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("initial_preissued_amount")
    private BigDecimal initialPreissuedAmount;
    
    public BigDecimal getInitialPreissuedAmount() {
        return initialPreissuedAmount;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean hasAttributes() {
        return             asset != null &&
            policies != null &&
            preIssuanceAssetSigner != null &&
            maxIssuanceAmount != null &&
            initialPreissuedAmount != null &&
            details != null 
        ;
    }
}
