package com.dulceyson.juezVirtual.domain.models;

import java.util.Set;

public class Usuario {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    private boolean activo;

    // Nuevos atributos
    private String affiliation;
    private String mail;
    private String policy;
    private String url;
    private String country;

    // Constructores
    public Usuario() {}

    public Usuario(Long id, String username, String email, String password, Set<String> roles, boolean activo,
                   String affiliation, String mail, String policy, String url, String country) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.activo = activo;
        this.affiliation = affiliation;
        this.mail = mail;
        this.policy = policy;
        this.url = url;
        this.country = country;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getAffiliation() { return affiliation; }
    public void setAffiliation(String affiliation) { this.affiliation = affiliation; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPolicy() { return policy; }
    public void setPolicy(String policy) { this.policy = policy; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}