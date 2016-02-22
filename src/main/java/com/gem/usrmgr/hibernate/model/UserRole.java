package com.gem.usrmgr.hibernate.model;

import javax.persistence.*;

/**
 * Created by kimtung on 2/17/16.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "user_role_id", unique = true, nullable = false)
    @SequenceGenerator(name = "user_role_id_seq", sequenceName = "user_role_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_id_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column(name = "role", nullable = false)
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role='" + role + '\'' +
                '}';
    }
}
