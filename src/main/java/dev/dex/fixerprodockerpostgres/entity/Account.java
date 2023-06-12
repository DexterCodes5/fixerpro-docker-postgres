package dev.dex.fixerprodockerpostgres.entity;

import dev.dex.fixerprodockerpostgres.oauth2.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "email")
    private String email;

    @Column(name = "hash_code")
    private Integer hashCode;

    @Enumerated(EnumType.STRING)
    private Provider provider;
}
