package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    @Min(0)
    private int id;
    @Size(min = 4,max = 20, message = "Username must consist of at least 3 and no more than 20 characters")
    private String username;
    @Size(min = 6,max = 50, message = "Username must consist of at least 3 and no more than 20 characters")
    private String password;

    private Role role;

    public User(@Size(min = 4, max = 20, message = "Username must consist of at least 3 and no more than 20 characters") String username, @Size(min = 6, max = 50, message = "Username must consist of at least 3 and no more than 20 characters") String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}
