package com.zambetti.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public boolean isAccountActive(){
        return password != null;
    }

    public enum Role {
        ADMIN,
        MANAGER,
        EMPLOYEE;
    }
}
