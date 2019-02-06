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


@Type("request-details-update-sale-end-time")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSaleEndTimeRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("new_end_time")
    private Date newEndTime;
    
    public Date getNewEndTime() {
        return newEndTime;
    }
    
    @Override
    public boolean hasAttributes() {
        return             newEndTime != null 
        ;
    }
    
    @Relationship("sale")
    private SaleResource sale;
    
    public SaleResource getSale() {
        return sale;
    }
}
