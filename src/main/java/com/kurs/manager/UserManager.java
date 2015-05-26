package com.kurs.manager;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import com.kurs.dao.UserDao;
import com.kurs.model.User;
import com.kurs.model.form.Register;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Named
@ManagedBean
public class UserManager {

	@Inject
	public UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(Register model) {
		userDao.addUser(model);
	}

    public boolean getIsAuthenticated()
    {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public UserDetails getCurrentUser()
    {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getCurrentEmail()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}