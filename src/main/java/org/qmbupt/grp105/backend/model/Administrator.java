package org.qmbupt.grp105.backend.model;

public class Administrator {
    protected String adminId;
    protected String name;
    protected String password;
    protected String phoneNo;
    protected String email;
    public org.qmbupt.grp105.Entity.Administrator converter() {
        return new org.qmbupt.grp105.Entity.Administrator(
                this.adminId, this.name, this.password, this.phoneNo, this.email
        );
    }
}
