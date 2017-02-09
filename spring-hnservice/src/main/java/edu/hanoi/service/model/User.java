package edu.hanoi.service.model;

import javax.persistence.*;

/**
 * Created by trungdovan on 12/2/16.
 */
@Entity
@Table(name="HN_USER", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
    private String username;
    private String password;

    private String email;
    private int age;
    private int groupId;

    @Id
    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "groupId", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
