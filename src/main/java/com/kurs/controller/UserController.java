package com.kurs.controller;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.faces.bean.ManagedBean;

import com.kurs.manager.UserManager;
import com.kurs.model.form.Login;
import com.kurs.model.form.Register;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@RequestScoped
public class UserController {

	@Inject
	public UserManager userManager;
    public UserManager getUserManager() {
        return userManager;
    }
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	private Register registerForm = new Register();

	public Register getRegisterForm() {
		return registerForm;
	}

	public void setRegisterForm(Register registerForm) {
		this.registerForm = registerForm;
	}

    private Login loginForm = new Login();

    public Login getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(Login loginForm) {
        this.loginForm = loginForm;
    }

    public String register() {
		try {
			if (!new BCryptPasswordEncoder().matches(registerForm.getPasswordConfirm(), registerForm.getPassword())) {
				throw new Exception("Passwords do not match!");
			}
			userManager.addUser(registerForm);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failure, " + e.getMessage(), ""));
			return null;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Success!", ""));
		registerForm.reset();
		return "login";
	}

    public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login failed", ""));
            return null;
            //return "incorrect";
        }
        return "correct";
    }

    public String logout(){
        SecurityContextHolder.clearContext();
        return "loggedout";
    }
}
