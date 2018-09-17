package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
@Data
@Entity
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountEnable;
    private boolean isCredentialNonExpired;
    private boolean isAccountNonLocked;
    private String role;


    public Admin(String username, String password, boolean isAccountNonExpired, boolean isAccountEnable, boolean isCredentialNonExpired, boolean isAccountNonLocked) {
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountEnable = isAccountEnable;
        this.isCredentialNonExpired = isCredentialNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.role = "ROLE_ADMIN";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

     return    AuthorityUtils.createAuthorityList(role);

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isAccountEnable;
    }
}
