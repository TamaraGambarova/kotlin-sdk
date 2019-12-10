// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-update-reviewable-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateReviewableRequestOpResource extends BaseOperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @Relationship("operations")
    private List<BaseOperationDetailsResource> operations;
    
    public List<? extends BaseOperationDetailsResource> getOperations() {
        return operations;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
