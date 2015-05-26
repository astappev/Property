package com.kurs.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="pr_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false, length = 45)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 60)
	private String password_hash;

    @Column(name = "phone", nullable = true, length = 20)
	private String phone;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> roles = new HashSet<UserRole>(0);

	public int getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return password_hash;
	}

	public void setPasswordHash(String pwd) {
		this.password_hash = pwd;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean  enabled) {
		this.enabled = enabled;
	}

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

}
