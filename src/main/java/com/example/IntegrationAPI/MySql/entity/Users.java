package com.example.IntegrationAPI.MySql.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.* ;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name="users")
public class Users {

    @Id
    private Long id;

    private String login;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    private int admin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    @Column(name = "last_login_on")
    private LocalDateTime lastlogin;
    private String type ;

    public Users(Long id, String login, String firstname, String lastname, int admin, LocalDateTime lastlogin, String type) {
        this.id = id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.admin = admin;
        this.lastlogin = lastlogin;
        this.type = type;
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAdmin() {
        return admin;
    }

    public LocalDateTime getLastlogin() {
        return lastlogin;
    }

    public String getType() {
        return type;
    }
}
