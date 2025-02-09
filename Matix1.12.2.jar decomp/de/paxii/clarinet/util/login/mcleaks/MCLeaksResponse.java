// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login.mcleaks;

import java.util.HashMap;

public class MCLeaksResponse
{
    private boolean success;
    private String errorMessage;
    private HashMap<String, String> result;
    
    public MCLeaksResponse() {
        this.result = new HashMap<String, String>();
    }
    
    public String getUsername() {
        return this.result.get("mcname");
    }
    
    public String getSession() {
        return this.result.get("session");
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public HashMap<String, String> getResult() {
        return this.result;
    }
    
    public void setSuccess(final boolean success) {
        this.success = success;
    }
    
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public void setResult(final HashMap<String, String> result) {
        this.result = result;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCLeaksResponse)) {
            return false;
        }
        final MCLeaksResponse other = (MCLeaksResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isSuccess() != other.isSuccess()) {
            return false;
        }
        final Object this$errorMessage = this.getErrorMessage();
        final Object other$errorMessage = other.getErrorMessage();
        Label_0078: {
            if (this$errorMessage == null) {
                if (other$errorMessage == null) {
                    break Label_0078;
                }
            }
            else if (this$errorMessage.equals(other$errorMessage)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$result = this.getResult();
        final Object other$result = other.getResult();
        if (this$result == null) {
            if (other$result == null) {
                return true;
            }
        }
        else if (this$result.equals(other$result)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof MCLeaksResponse;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        final Object $errorMessage = this.getErrorMessage();
        result = result * 59 + (($errorMessage == null) ? 43 : $errorMessage.hashCode());
        final Object $result = this.getResult();
        result = result * 59 + (($result == null) ? 43 : $result.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "MCLeaksResponse(success=" + this.isSuccess() + ", errorMessage=" + this.getErrorMessage() + ", result=" + this.getResult() + ")";
    }
}
