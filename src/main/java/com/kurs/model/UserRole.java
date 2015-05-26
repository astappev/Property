package com.kurs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pr_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int roleId) {
		this.id = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String code) {
		this.role = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
