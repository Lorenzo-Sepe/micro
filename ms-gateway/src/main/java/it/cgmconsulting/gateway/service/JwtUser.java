package it.cgmconsulting.gateway.service;

public class JwtUser {

    private String id;
    private String role;
    private String username;

    public JwtUser() {
    }

    public JwtUser(String id, String role, String username) {
        this.id = id;
        this.role = role;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
