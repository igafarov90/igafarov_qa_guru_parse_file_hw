package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("sup_acl")
    private SupAcl supAcl;


    @JsonProperty("full_name")
    private String fullName;

    private String login;

    public SupAcl getSupAcl() {
        return supAcl;
    }

    public void setSupAcl(SupAcl supAcl) {
        this.supAcl = supAcl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

}
