package com.chgkportal.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ROLE_USER(Set.of(Permission.DEFAULT_READ)),
    ROLE_ADMIN(Set.of(Permission.ADMIN_PANEL_CRUD, Permission.DEFAULT_READ, Permission.DEFAULT_WRITE));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
