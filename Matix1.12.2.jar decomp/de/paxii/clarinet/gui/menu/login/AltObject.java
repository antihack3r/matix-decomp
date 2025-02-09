// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.menu.login;

public class AltObject implements Comparable<AltObject>
{
    private String userName;
    private String email;
    private String password;
    
    public AltObject(final String userName, final String email, final String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    
    public AltObject(final String userName, final String password) {
        this(userName, "", password);
    }
    
    public AltObject(final String userName) {
        this(userName, "");
    }
    
    public boolean isPremium() {
        return this.password.length() > 0;
    }
    
    public boolean isMojang() {
        return this.email.length() > 0;
    }
    
    @Override
    public int compareTo(final AltObject o) {
        return this.getUserName().compareToIgnoreCase(o.getUserName());
    }
    
    public AltObject copy() {
        return new AltObject(this.userName, this.email, this.password);
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AltObject)) {
            return false;
        }
        final AltObject other = (AltObject)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0065: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0065;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0102: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0102;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null) {
            if (other$password == null) {
                return true;
            }
        }
        else if (this$password.equals(other$password)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AltObject;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "AltObject(userName=" + this.getUserName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }
}
