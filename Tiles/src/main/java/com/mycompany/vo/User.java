package com.mycompany.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

public class User {

	int id;

	@NotNull
	String email;

	@NotNull
	String password;

	List<Authority> authorities;

	public boolean hasRole(String role) {
		for (Authority auth : authorities)
			if (auth.isRole(role))
				return true;
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", authorities=" + authorities + "]";
	}
}