// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.recpayments.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.resources.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("recurring-payments-svc-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("account_id")
    private BaseResource accountId;
    
    public BaseResource getAccountId() {
        return accountId;
    }
    
    @Relationship("signer_pub_key")
    private BaseResource signerPubKey;
    
    public BaseResource getSignerPubKey() {
        return signerPubKey;
    }
}
