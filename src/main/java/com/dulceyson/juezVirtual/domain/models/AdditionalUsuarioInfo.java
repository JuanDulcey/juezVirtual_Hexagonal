package com.dulceyson.juezVirtual.domain.models;

public class AdditionalUsuarioInfo {
    private final Long id;
    private final String username;
    private final String email;


    public AdditionalUsuarioInfo(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
