package com.kurs.model.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Register {

	private String name;
	private String email;
	private String password;
	private String passwordConfirm;
	private String phone;

	public void reset() {
		this.name = null;
		this.email = null;
		this.password = null;
		this.passwordConfirm = null;
		this.phone = null;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
