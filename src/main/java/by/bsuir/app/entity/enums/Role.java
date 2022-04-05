package by.bsuir.app.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(1, Set.of(Permission.DEVELOPERS_WRITE)),
    USER(2, Set.of(Permission.DEVELOPERS_READ)),
    DOCTOR(3, Set.of(Permission.DEVELOPERS_READ));

    private final int roleCode;
    private final Set<Permission> permissions;

    Role(int roleCode, Set<Permission> permissions) {
        this.roleCode = roleCode;
        this.permissions = permissions;
    }

    public int getRoleCode() {
        return roleCode;
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
