package org.treeleaf.dto;

import lombok.Data;
import org.treeleaf.entity.Permission;
import org.treeleaf.entity.Role;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private List<Permission> permissions;
}
