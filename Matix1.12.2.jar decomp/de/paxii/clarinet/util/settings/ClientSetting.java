// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

public class ClientSetting<T> implements Comparable<ClientSetting>
{
    private String name;
    private T value;
    
    public ClientSetting(final String name, final T value) {
        this.name = name;
        this.value = value;
    }
    
    public void setValue(final T value) {
        if (value != this.value || !this.value.equals(value)) {
            this.onUpdate(value, this.value);
        }
        this.value = value;
    }
    
    public void onUpdate(final T newValue, final T oldValue) {
    }
    
    @Deprecated
    public String getSettingName() {
        return this.name;
    }
    
    @Deprecated
    public void setSettingName(final String settingName) {
        this.name = settingName;
    }
    
    @Deprecated
    public T getSettingValue() {
        return this.value;
    }
    
    @Deprecated
    public void setSettingValue(final T settingValue) {
        this.value = settingValue;
    }
    
    @Override
    public int compareTo(final ClientSetting otherSetting) {
        return this.getName().compareTo(otherSetting.getName());
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ClientSetting)) {
            return false;
        }
        final ClientSetting<?> other = (ClientSetting<?>)o;
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
        return other instanceof ClientSetting;
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
        return "ClientSetting(name=" + this.getName() + ", value=" + this.getValue() + ")";
    }
}
