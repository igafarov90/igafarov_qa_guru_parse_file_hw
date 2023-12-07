package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SupAcl {

    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    }

