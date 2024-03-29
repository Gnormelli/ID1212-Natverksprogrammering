package com.ID1212.ID1212_Project_Intelij.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user_info")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String username;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String password;

    @Column(
            name = "user_email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String email;

    @ManyToOne
    @JoinColumn(name = "fk_id_role")
    Role userRole;

    @ManyToOne
    @JoinColumn(name = "fk_id_profile_picture")
    ProfilePicture profilePicture;

    @Column(
            name = "locked",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean locked;

    @Column(
            name = "unlocked",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean enabled;

    protected User(){}

    public User(String username,
                String password,
                String email,
                Role userRole,
                ProfilePicture profilePicture,
                Boolean locked,
                Boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.profilePicture = profilePicture;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", profilePicture='" + profilePicture + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName());
        return Collections.singletonList(authority);
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean unlocked) {
        this.enabled = unlocked;
    }
}
