package com.kurs.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kurs.dao.UserDao;
import com.kurs.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.kurs.model.User user = userDao.findByUserName(username);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.kurs.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPasswordHash(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
