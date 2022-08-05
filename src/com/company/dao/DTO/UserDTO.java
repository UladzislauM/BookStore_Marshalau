package com.company.dao.DTO;

import com.company.dao.entity.RoleUser;
import com.company.dao.entity.User;

import java.util.Objects;

public class UserDTO {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUserDTO role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public RoleUserDTO getRole() {
        return role;
    }

    public void setRole(RoleUserDTO role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(last_name, userDTO.last_name) && Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password) && role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, last_name, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
