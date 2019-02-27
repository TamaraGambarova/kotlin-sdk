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


@Type("operations-cancel-aswap-bid")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCancelAtomicSwapBidDetailsResource extends OperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @Relationship("bid")
    private ASwapBidRequestDetailsResource bid;
    
    public ASwapBidRequestDetailsResource getBid() {
        return bid;
    }
}
