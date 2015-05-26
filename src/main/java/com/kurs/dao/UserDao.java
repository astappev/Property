package com.kurs.dao;

import javax.inject.Inject;
import javax.inject.Named;

import com.kurs.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kurs.model.form.Register;
import com.kurs.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@Transactional("transactionManager")
public class UserDao {

	@Inject
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		List<User> users = new ArrayList<User>();

		users = openSession().createQuery("from User u where u.email = ?")
				.setParameter(0, username)
				.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	public void addUser(Register model) {
		User user = new User();
		user.setName(model.getName());
		user.setEmail(model.getEmail());
		user.setPasswordHash(model.getPassword());
		user.setPhone(model.getPhone());
		user.setEnabled(true);
		user.setUpdatedAt(new Date());
		user.setCreatedAt(new Date());
		sessionFactory.getCurrentSession().save(user);

		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
        role.setUser(user);
		sessionFactory.getCurrentSession().save(role);
	}
}
