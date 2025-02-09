// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.update;

import java.util.ArrayList;

public class VersionList
{
    private ArrayList<VersionObject> versions;
    
    public ArrayList<VersionObject> getVersions() {
        return this.versions;
    }
    
    public void setVersions(final ArrayList<VersionObject> versions) {
        this.versions = versions;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VersionList)) {
            return false;
        }
        final VersionList other = (VersionList)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$versions = this.getVersions();
        final Object other$versions = other.getVersions();
        if (this$versions == null) {
            if (other$versions == null) {
                return true;
            }
        }
        else if (this$versions.equals(other$versions)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof VersionList;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $versions = this.getVersions();
        result = result * 59 + (($versions == null) ? 43 : $versions.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "VersionList(versions=" + this.getVersions() + ")";
    }
}
