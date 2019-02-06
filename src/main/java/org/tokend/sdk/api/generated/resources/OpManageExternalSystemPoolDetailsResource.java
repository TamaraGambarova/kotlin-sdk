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


@Type("operations-manage-external-system-account-id-pool-entry")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageExternalSystemPoolDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private ExternalSystemPoolCreation create;
    
    @Nullable
    public ExternalSystemPoolCreation getCreate() {
        return create;
    }
    
    @JsonProperty("remove")
    @Nullable
    private ExternalSystemPoolRemoval remove;
    
    @Nullable
    public ExternalSystemPoolRemoval getRemove() {
        return remove;
    }
    
    @Override
    public boolean hasAttributes() {
        return             action != null 
        ;
    }
}
