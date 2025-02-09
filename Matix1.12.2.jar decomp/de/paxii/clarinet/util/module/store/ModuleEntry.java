// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.store;

public class ModuleEntry
{
    private String module;
    private String description;
    private String version;
    private String compatible;
    private int build;
    
    public String getModule() {
        return this.module;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getCompatible() {
        return this.compatible;
    }
    
    public int getBuild() {
        return this.build;
    }
    
    public void setModule(final String module) {
        this.module = module;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public void setCompatible(final String compatible) {
        this.compatible = compatible;
    }
    
    public void setBuild(final int build) {
        this.build = build;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModuleEntry)) {
            return false;
        }
        final ModuleEntry other = (ModuleEntry)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$module = this.getModule();
        final Object other$module = other.getModule();
        Label_0065: {
            if (this$module == null) {
                if (other$module == null) {
                    break Label_0065;
                }
            }
            else if (this$module.equals(other$module)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        Label_0102: {
            if (this$description == null) {
                if (other$description == null) {
                    break Label_0102;
                }
            }
            else if (this$description.equals(other$description)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        Label_0139: {
            if (this$version == null) {
                if (other$version == null) {
                    break Label_0139;
                }
            }
            else if (this$version.equals(other$version)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$compatible = this.getCompatible();
        final Object other$compatible = other.getCompatible();
        if (this$compatible == null) {
            if (other$compatible == null) {
                return this.getBuild() == other.getBuild();
            }
        }
        else if (this$compatible.equals(other$compatible)) {
            return this.getBuild() == other.getBuild();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ModuleEntry;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $module = this.getModule();
        result = result * 59 + (($module == null) ? 43 : $module.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $compatible = this.getCompatible();
        result = result * 59 + (($compatible == null) ? 43 : $compatible.hashCode());
        result = result * 59 + this.getBuild();
        return result;
    }
    
    @Override
    public String toString() {
        return "ModuleEntry(module=" + this.getModule() + ", description=" + this.getDescription() + ", version=" + this.getVersion() + ", compatible=" + this.getCompatible() + ", build=" + this.getBuild() + ")";
    }
}
