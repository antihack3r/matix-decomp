// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

public class ClientSettingSettingsObject
{
    private String name;
    private Object value;
    
    public ClientSettingSettingsObject(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ClientSettingSettingsObject)) {
            return false;
        }
        final ClientSettingSettingsObject other = (ClientSettingSettingsObject)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0065: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0065;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null) {
            if (other$value == null) {
                return true;
            }
        }
        else if (this$value.equals(other$value)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ClientSettingSettingsObject;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $value = this.getValue();
        result = result * 59 + (($value == null) ? 43 : $value.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ClientSettingSettingsObject(name=" + this.getName() + ", value=" + this.getValue() + ")";
    }
}
