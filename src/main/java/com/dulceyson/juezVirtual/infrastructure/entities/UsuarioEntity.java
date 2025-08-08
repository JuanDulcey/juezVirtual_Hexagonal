package com.dulceyson.juezVirtual.infrastructure.entities;

import com.dulceyson.juezVirtual.domain.models.Usuario;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "rol")
    private Set<String> roles;

    @Column(nullable = false)
    private boolean activo = true;

    // Nuevos campos
    @Column(nullable = true, length = 100)
    private String affiliation;

    @Column(nullable = true, length = 100)
    private String mail;

    @Column(nullable = false, length = 20)
    private String policy;

    @Column(nullable = true, length = 255)
    private String url;

    @Column(nullable = false, length = 100)
    private String country;

    // Constructores
    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String username, String email, String password, Set<String> roles, boolean activo,
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

    public static UsuarioEntity fromDomainModel(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRoles(),
                usuario.isActivo(),
                usuario.getAffiliation(),
                usuario.getMail(),
                usuario.getPolicy(),
                usuario.getUrl(),
                usuario.getCountry()
        );
    }

    public Usuario toDomainModel() {
        return new Usuario(id, username, email, password, roles, activo, affiliation, mail, policy, url, country);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}