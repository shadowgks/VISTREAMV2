package com.example.vistreamv2.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userName", "email"})
})
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userNamee;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime accessionDate;
    private Boolean isEnabled;

//    @OneToMany(mappedBy = "appUsers", fetch = FetchType.EAGER)
//    private List<Watchlist> watchlists;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "watchlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id"))
    private Set<Media> watchlists;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users-groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<PermissionGroup> permissionGroups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName()))
        );

        roles.forEach(role -> role.getPermissions()
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority("PERMISSION_"+ permission.getSubject() + ":" +permission.getAction())))
        );

        permissionGroups.forEach(group -> group.getPermissions()
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority("PERMISSION_"+ permission.getSubject() + ":" +permission.getAction())))
        );
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
