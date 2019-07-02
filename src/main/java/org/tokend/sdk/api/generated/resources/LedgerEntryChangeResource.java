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


@Type("ledger-entry-changes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LedgerEntryChangeResource extends BaseResource {
    
    @JsonProperty("change_type")
    private XdrEnumValue changeType;
    
    public XdrEnumValue getChangeType() {
        return changeType;
    }
    
    @JsonProperty("entry_type")
    private XdrEnumValue entryType;
    
    public XdrEnumValue getEntryType() {
        return entryType;
    }
    
    @JsonProperty("payload")
    private String payload;
    
    public String getPayload() {
        return payload;
    }
    
    @Override
    public boolean isFilled() {
        return             changeType != null &&
            entryType != null &&
            payload != null 
        ;
    }
}
